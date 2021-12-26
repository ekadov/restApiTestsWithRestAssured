package com.restfulbooker.pojos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateBookingResponsePojo {

    @JsonProperty("booking")
    private Booking booking;

    @JsonProperty("bookingid")
    private int bookingid;

}