package com.restfulbooker.steps;

import com.restfulbooker.pojos.request.create_booking.BookingRequestPojo;
import com.restfulbooker.pojos.response.ListOfBookingsResponsePojo;
import com.restfulbooker.utils.Endpoints;
import com.restfulbooker.utils.specifications.Specifications;
import io.restassured.response.Response;

import java.util.List;

public class UpdateBookingStep {

    public Response sendUpdateFirstBookingFromTheListRequest(BookingRequestPojo generatedBooking) {
        Specifications specification = new Specifications();
        GetBookingsListStep getBookingsListStep = new GetBookingsListStep();
        AuthStep authStep = new AuthStep();

        List<ListOfBookingsResponsePojo> listOfBookings =
                getBookingsListStep.sendGetBookingsListRequestReturnList();
        int specificBooking = listOfBookings.get(0).getBookingId();

        return specification.getSpecification()
                .cookie("token", authStep.sendAuthRequestReturnToken())
                .body(generatedBooking)
                .when()
                .put(Endpoints.BOOKING_ID, specificBooking);
    }

}
