package negative_tests;


import base_class.BaseTestClass;
import com.restfulbooker.steps.AuthStep;
import com.restfulbooker.steps.CreateBookingStep;
import com.restfulbooker.steps.GetSpecificBookingStep;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;


class NegativeTests extends BaseTestClass {

    @Test
    void testCanNotAuthWithInvalidCredentialsCheckStatusCode() {
        AuthStep authStep = new AuthStep();

        authStep.sendAuthRequestWithInvalidCredentials()
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    void testCanNotAuthWithInvalidCredentialsCheckBody() {
        AuthStep authStep = new AuthStep();

        authStep.sendAuthRequestWithInvalidCredentials()
                .then()
                .assertThat()
                .body("reason", is("Bad credentials"));
    }

    @Test
    void testCanNotGetBookingWhichNotExistsCheckStatusCode() {
        int specificBooking = 0;
        GetSpecificBookingStep getSpecificBookingStep = new GetSpecificBookingStep();

        getSpecificBookingStep.sendGetBookingByIdRequest(specificBooking)
                .then().assertThat().statusCode(404);
    }

    @Test
    void testCanNotGetBookingWhichNotExistsCheckBody() {
        int specificBooking = 0;
        GetSpecificBookingStep getSpecificBookingStep = new GetSpecificBookingStep();

        getSpecificBookingStep.sendGetBookingByIdRequest(specificBooking)
                .then().assertThat().body(is("Not Found"));
    }

    @Test
    void testCanNotCreateBookingWithWrongCheckinDateCheckBody() {
        CreateBookingStep createBookingStep = new CreateBookingStep();

        createBookingStep.sendCreateBookingRequest(generatedWrongBooking)
                .then()
                .assertThat().body(is("Invalid date"));
    }

}

   


