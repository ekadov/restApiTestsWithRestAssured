package com.restfulbooker.steps;

import com.restfulbooker.utils.Endpoints;
import com.restfulbooker.utils.specifications.Specifications;
import io.restassured.response.Response;

public class GetSpecificBookingStep {

    public Response sendGetBookingByIdRequest(int bookingId) {
        Specifications specification = new Specifications();

        return specification.getSpecification()
                .when()
                .get(Endpoints.BOOKING_ID, bookingId);
    }

}
