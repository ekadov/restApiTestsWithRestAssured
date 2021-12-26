package positive_tests;


import base_class.BaseTestClass;
import com.fasterxml.jackson.core.type.TypeReference;
import com.restfulbooker.pojos.request.create_booking.BookingRequestPojo;
import com.restfulbooker.pojos.response.CreateBookingResponsePojo;
import com.restfulbooker.pojos.response.ListOfBookingsResponsePojo;
import com.restfulbooker.steps.*;
import com.restfulbooker.utils.Endpoints;
import com.restfulbooker.utils.GenerateData;
import com.restfulbooker.utils.Smoke;
import com.restfulbooker.utils.specifications.Specifications;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class PositiveTests extends BaseTestClass {

    @Test
    void testPing() {
        Specifications specification = new Specifications();

        specification.getSpecification()
                .when().get(Endpoints.PING)
                .then()
                .assertThat().statusCode(201);
    }

    @Test
    @Smoke
    void testCanAuthWithValidCredentialsCheckStatusCode() {
        AuthStep authStep = new AuthStep();

        authStep.sendAuthRequestWithValidCredentials()
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    @Smoke
    void testCanAuthWithValidCredentialsCheckBodyIsNotEmpty() {
        AuthStep authStep = new AuthStep();

        authStep.sendAuthRequestWithValidCredentials()
                .then()
                .assertThat()
                .body("token", not(0));
    }

    @Test
    void testCanGetAllBookingsCheckStatusCode() {
        GetBookingsListStep getBookingsListStep = new GetBookingsListStep();

        getBookingsListStep.sendGetBookingsListRequest()
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    void testCanGetAllBookingsCheckBodyIsNotEmpty() {
        GetBookingsListStep getBookingsListStep = new GetBookingsListStep();

        getBookingsListStep.sendGetBookingsListRequest()
                .then()
                .assertThat()
                .body("isEmpty()", Matchers.is(false));
    }

    @Test
    void testCanGetSpecificBookingCheckStatusCode() {
        GetBookingsListStep getBookingsListStep = new GetBookingsListStep();
        GetSpecificBookingStep getSpecificBookingStep = new GetSpecificBookingStep();
        List<ListOfBookingsResponsePojo> listOfBookings;

        listOfBookings =
                getBookingsListStep.sendGetBookingsListRequestReturnList();
        int specificBooking = listOfBookings.get(0).getBookingId();

        getSpecificBookingStep.sendGetBookingByIdRequest(specificBooking)
                .then().assertThat().statusCode(200);
    }

    @Test
    void testCanCreateBookingCheckStatusCode() {
        CreateBookingStep createBookingStep = new CreateBookingStep();

        createBookingStep.sendCreateBookingRequest(generatedBooking)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    void testCanCreateBookingCheckReturnedIdIsNotNull() {
        CreateBookingResponsePojo createBookingResponsePojo;
        CreateBookingStep createBookingStep = new CreateBookingStep();

        createBookingResponsePojo = createBookingStep
                .sendCreateBookingRequest(generatedBooking)
                .then()
                .extract().as(CreateBookingResponsePojo.class);

        assertThat(createBookingResponsePojo)
                .extracting(CreateBookingResponsePojo::getBookingid)
                .isNotNull();
    }

    @Test
    void testCanUpdateBookingCheckStatusCode() {
        UpdateBookingStep updateBookingStep = new UpdateBookingStep();

        updateBookingStep.sendUpdateFirstBookingFromTheListRequest(generatedBooking)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    void testCanUpdateBookingCheckIfUpdated() {
        BookingRequestPojo updatedBooking;
        List<ListOfBookingsResponsePojo> listOfBookings;
        GetBookingsListStep getBookingsListStep = new GetBookingsListStep();
        GetSpecificBookingStep getSpecificBookingStep = new GetSpecificBookingStep();
        UpdateBookingStep updateBookingStep = new UpdateBookingStep();

        listOfBookings =
                getBookingsListStep.sendGetBookingsListRequestReturnList();
        int specificBooking = listOfBookings.get(0).getBookingId();

        updateBookingStep.sendUpdateFirstBookingFromTheListRequest(generatedBooking);

        updatedBooking = getSpecificBookingStep
                .sendGetBookingByIdRequest(specificBooking)
                .as(BookingRequestPojo.class);

        Assertions.assertEquals(generatedBooking, updatedBooking);
    }

    @Test
    void testCanUpdateBookingPartialCheckStatusCode() {
        BookingRequestPojo beforeUpdateBooking;
        BookingRequestPojo generatedBookingPartial;
        List<ListOfBookingsResponsePojo> listOfBookings;
        GetBookingsListStep getBookingsListStep = new GetBookingsListStep();
        GetSpecificBookingStep getSpecificBookingStep = new GetSpecificBookingStep();
        PartialBookingUpdateStep partialBookingUpdateStep = new PartialBookingUpdateStep();

        listOfBookings = getBookingsListStep.sendGetBookingsListRequestReturnList();
        int specificBooking = listOfBookings.get(0).getBookingId();

        beforeUpdateBooking =
                getSpecificBookingStep.sendGetBookingByIdRequest(specificBooking)
                        .as(BookingRequestPojo.class);
        generatedBookingPartial =
                GenerateData.generateNewBookingPartial(beforeUpdateBooking);

        partialBookingUpdateStep.sendBookingPartialUpdateRequest(generatedBookingPartial)
                .then().assertThat().statusCode(200);
    }

    @Test
    void testCanUpdateBookingPartialCheckIfUpdated() {
        BookingRequestPojo beforeUpdateBooking;
        BookingRequestPojo afterUpdateBooking;
        List<ListOfBookingsResponsePojo> listOfBookings;
        GetBookingsListStep getBookingsListStep = new GetBookingsListStep();
        GetSpecificBookingStep getSpecificBookingStep = new GetSpecificBookingStep();
        PartialBookingUpdateStep partialBookingUpdateStep = new PartialBookingUpdateStep();

        Type type = new TypeReference<List<ListOfBookingsResponsePojo>>() {
        }.getType();
        listOfBookings = getBookingsListStep.sendGetBookingsListRequest().as(type);
        int firstBookingId = listOfBookings.get(0).getBookingId();

        beforeUpdateBooking =
                getSpecificBookingStep.sendGetBookingByIdRequest(firstBookingId)
                        .as(BookingRequestPojo.class);
        BookingRequestPojo generatedBookingPartial =
                GenerateData.generateNewBookingPartial(beforeUpdateBooking);

        partialBookingUpdateStep.sendBookingPartialUpdateRequest(generatedBookingPartial);

        getSpecificBookingStep.sendGetBookingByIdRequest(firstBookingId);

        afterUpdateBooking =
                getSpecificBookingStep.sendGetBookingByIdRequest(firstBookingId)
                        .as(BookingRequestPojo.class);

        assertEquals(generatedBookingPartial, afterUpdateBooking);
    }

    @Test
    void testCanDeleteBooking() {
        int firstBookingId;
        List<ListOfBookingsResponsePojo> listOfBookingsBefore;
        List<ListOfBookingsResponsePojo> listOfBookingsAfter;
        Specifications specification = new Specifications();
        AuthStep authStep = new AuthStep();
        GetBookingsListStep getBookingsListStep = new GetBookingsListStep();

        listOfBookingsBefore =
                getBookingsListStep.sendGetBookingsListRequestReturnList();
        firstBookingId = listOfBookingsBefore.get(0).getBookingId();
        specification.getSpecification()
                .cookie("token", authStep.sendAuthRequestReturnToken())
                .when()
                .delete(Endpoints.BOOKING_ID, firstBookingId)
                .then().assertThat().statusCode(201);
        listOfBookingsAfter =
                getBookingsListStep.sendGetBookingsListRequestReturnList();
        assertNotEquals(listOfBookingsBefore.size(), listOfBookingsAfter.size());

    }


}

   


