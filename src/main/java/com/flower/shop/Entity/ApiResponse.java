package com.flower.shop.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse {

    @JsonProperty("success")
    private boolean sucess;

    @JsonProperty("message")
    private String message;
    
    public ApiResponse(boolean sucess, String message) {
        this.sucess = sucess;
        this.message = message;
    }

}
