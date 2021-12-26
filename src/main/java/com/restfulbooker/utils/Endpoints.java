package com.restfulbooker.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Endpoints {

    public static final String BOOKING = "/booking/";
    public static final String BOOKING_ID = BOOKING + "{id}";
    public static final String PING = "/ping";
    public static final String AUTHORIZATION = "/auth";

}
