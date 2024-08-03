package com.podosoft.Request;

public class RegisterResponse {
    private String body;
    private String status;

    public RegisterResponse(String body, String status) {
        this.body = body;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
