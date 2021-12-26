package com.restfulbooker.utils.specifications;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specifications {

    public RequestSpecification getSpecification() {
        return given()
                .filter(new RequestLoggingFilter(LogDetail.URI))
                .filter(new RequestLoggingFilter(LogDetail.METHOD))
                .filter(new RequestLoggingFilter(LogDetail.BODY))
                .filter(new ResponseLoggingFilter(LogDetail.STATUS))
                .filter(new ResponseLoggingFilter(LogDetail.BODY))
                .contentType(ContentType.JSON);
    }


}
