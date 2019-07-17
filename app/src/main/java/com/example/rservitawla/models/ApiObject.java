package com.example.rservitawla.models;

public class ApiObject {

    private String status;
    private String message;
    private ApiData data;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ApiData getData() {
        return data;
    }
    public void setData(ApiData data) {
        this.data = data;
    }
}
