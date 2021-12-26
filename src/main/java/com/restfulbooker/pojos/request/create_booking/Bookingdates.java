package com.restfulbooker.pojos.request.create_booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Setter
@Accessors(fluent = true)
public class Bookingdates {

    @JsonProperty("checkin")
    private String checkin;

    @JsonProperty("checkout")
    private String checkout;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookingdates that = (Bookingdates) o;
        return this.checkin.equals(that.checkin) &&
                this.checkout.equals(that.checkout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkin, checkout);
    }
}