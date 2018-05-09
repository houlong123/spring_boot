package com.example.demo.entity;

/**
 * Created by houlong on 2018/5/8.
 */
public class ResponseEntity {

    public static final Integer OK = 1;
    public static final Integer ERROR = 0;

    private int code;
    private String message;
    private String url;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
