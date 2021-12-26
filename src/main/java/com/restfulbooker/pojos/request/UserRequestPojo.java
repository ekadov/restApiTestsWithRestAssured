package com.restfulbooker.pojos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserRequestPojo {

    @JsonProperty("password")
    private String password;

    @JsonProperty("username")
    private String username;

    public UserRequestPojo setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRequestPojo setUsername(String username) {
        this.username = username;
        return this;
    }
}