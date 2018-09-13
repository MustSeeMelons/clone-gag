package com.strautins.CloneGag.exceptions;

/**
 * Wrapper exception for REST endpoints.
 */
public class RestException extends Exception {
    private Exception e;
    private Integer code;

    public RestException(int code, String message) {
        super(message);
        this.code = code;
    }

    public RestException(Exception e) {
        super();
        this.e = e;
    }

    public RestException(int code, String message, Exception e) {
        super(message);
        this.e = e;
        this.code = code;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
