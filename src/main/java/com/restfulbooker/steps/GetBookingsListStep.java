package com.restfulbooker.steps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.restfulbooker.pojos.response.ListOfBookingsResponsePojo;
import com.restfulbooker.utils.Endpoints;
import com.restfulbooker.utils.specifications.Specifications;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetBookingsListStep {

    public Response sendGetBookingsListRequest() {
        Specifications specification = new Specifications();
        return specification.getSpecification()
                .when()
                .get(Endpoints.BOOKING);
    }

    public List<ListOfBookingsResponsePojo> sendGetBookingsListRequestReturnList() {
        Type type = new TypeReference<List<ListOfBookingsResponsePojo>>() {
        }.getType();
        return given()
                .when()
                .get(Endpoints.BOOKING).as(type);
    }
}
