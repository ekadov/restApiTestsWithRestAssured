package com.restfulbooker.steps;

import com.restfulbooker.pojos.request.create_booking.BookingRequestPojo;
import com.restfulbooker.utils.Endpoints;
import com.restfulbooker.utils.specifications.Specifications;
import io.restassured.response.Response;

public class CreateBookingStep {

    public Response sendCreateBookingRequest(BookingRequestPojo generatedBooking) {
        Specifications specification = new Specifications();
        return specification.getSpecification()
                .body(generatedBooking)
                .when()
                .post(Endpoints.BOOKING);
    }

}
