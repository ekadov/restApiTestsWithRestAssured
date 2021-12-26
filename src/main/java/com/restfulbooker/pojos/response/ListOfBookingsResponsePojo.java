package com.restfulbooker.pojos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ListOfBookingsResponsePojo {

    @JsonProperty("bookingid")
    private int bookingId;

}