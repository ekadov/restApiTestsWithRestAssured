package com.restfulbooker.steps;

import com.restfulbooker.pojos.request.UserRequestPojo;
import com.restfulbooker.utils.ConfProperties;
import com.restfulbooker.utils.Endpoints;
import com.restfulbooker.utils.specifications.Specifications;
import io.restassured.response.Response;

public class AuthStep {

    private static Specifications specification = new Specifications();

    public String sendAuthRequestReturnToken() {
        UserRequestPojo user = new UserRequestPojo()
                .setUsername(ConfProperties.getValidUsername())
                .setPassword(ConfProperties.getValidPassword());
        return specification.getSpecification()
                .body(user)
                .when()
                .post(Endpoints.AUTHORIZATION)
                .then()
                .extract().path("token");
    }

    public Response sendAuthRequestWithValidCredentials() {
        UserRequestPojo user = new UserRequestPojo()
                .setUsername(ConfProperties.getValidUsername())
                .setPassword(ConfProperties.getValidPassword());
        return specification.getSpecification()
                .body(user)
                .when()
                .post(Endpoints.AUTHORIZATION);
    }

    public Response sendAuthRequestWithInvalidCredentials() {
        UserRequestPojo user = new UserRequestPojo()
                .setUsername(ConfProperties.getInvalidUsername())
                .setPassword(ConfProperties.getInvalidPassword());
        return specification.getSpecification()
                .body(user)
                .when()
                .post(Endpoints.AUTHORIZATION);
    }

}
