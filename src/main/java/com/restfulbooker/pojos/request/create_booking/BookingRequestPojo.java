package com.restfulbooker.pojos.request.create_booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Setter
@Getter
@Accessors(fluent = true)
public class BookingRequestPojo {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("totalprice")
    private int totalprice;

    @JsonProperty("depositpaid")
    private boolean depositpaid;

    @JsonProperty("bookingdates")
    private Bookingdates bookingdates;

    @JsonProperty("additionalneeds")
    private String additionalneeds;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingRequestPojo that = (BookingRequestPojo) o;
        return this.totalprice == that.totalprice &&
                this.depositpaid == that.depositpaid &&
                this.firstname.equals(that.firstname) &&
                this.lastname.equals(that.lastname) &&
                this.bookingdates.equals(that.bookingdates) &&
                this.additionalneeds.equals(that.additionalneeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
    }

}