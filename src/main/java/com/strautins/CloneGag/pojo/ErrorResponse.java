package com.strautins.CloneGag.pojo;

import com.strautins.CloneGag.exceptions.RestException;

/**
 * REST endpoint error response.
 */
public class ErrorResponse {
    private String message;
    private int code;

    public ErrorResponse(RestException e) {
        this.message = e.getMessage();
        this.code = e.getCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
