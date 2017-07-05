package com.ywd.model;

/**
 * Created by admin on 2017/6/30.
 */
public class WiselyResponse {

    private String responseMessage;

    public WiselyResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
