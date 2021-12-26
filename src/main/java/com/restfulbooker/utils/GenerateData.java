package com.restfulbooker.utils;

import com.github.javafaker.Faker;
import com.restfulbooker.pojos.request.create_booking.BookingRequestPojo;
import com.restfulbooker.pojos.request.create_booking.Bookingdates;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerateData {

    private static final Faker FAKER = new Faker();
    private static boolean depositpaid = true;

    private static String generateFirstName() {
        return (FAKER.name().firstName());
    }

    private static String generateLastName() {
        return (FAKER.name().lastName());
    }

    private static int generateTotalPrice() {
        return (Integer.parseInt(FAKER.number().digits(3)));
    }

    private static String generateAdditionalNeeds() {
        return (FAKER.business().creditCardType());
    }

    private static String generateCheckinAndCheckoutDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(calendar.getTime());
    }

    public static BookingRequestPojo generateNewBooking() {
        return new BookingRequestPojo()
                .firstname(generateFirstName())
                .lastname(generateLastName())
                .totalprice(generateTotalPrice())
                .depositpaid(depositpaid)
                .bookingdates(generateNewBookingDates())
                .additionalneeds(generateAdditionalNeeds());
    }

    public static BookingRequestPojo generateNewBookingPartial(BookingRequestPojo beforeUpdateBooking) {
        return beforeUpdateBooking
                .firstname(generateFirstName())
                .lastname(generateLastName())
                .totalprice(beforeUpdateBooking.totalprice())
                .depositpaid(beforeUpdateBooking.depositpaid())
                .bookingdates(beforeUpdateBooking.bookingdates())
                .additionalneeds(beforeUpdateBooking.additionalneeds());
    }

    private static Bookingdates generateNewBookingDates() {
        return new Bookingdates()
                .checkin(generateCheckinAndCheckoutDate())
                .checkout(generateCheckinAndCheckoutDate());
    }

    public static BookingRequestPojo generateWrongBooking() {
        return new BookingRequestPojo()
                .firstname(generateFirstName())
                .lastname(generateLastName())
                .totalprice(generateTotalPrice())
                .depositpaid(depositpaid)
                .bookingdates(generateWrongBookingDates())
                .additionalneeds(generateAdditionalNeeds());
    }

    private static Bookingdates generateWrongBookingDates() {
        return new Bookingdates()
                .checkin("Wrong date")
                .checkout(generateCheckinAndCheckoutDate());
    }
}
