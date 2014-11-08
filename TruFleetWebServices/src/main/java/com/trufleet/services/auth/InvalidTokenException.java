package com.trufleet.services.auth;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
public class InvalidTokenException extends Exception {

    private String token;

    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(String message, String token) {
        super(message);
        this.token = token;
    }

    public InvalidTokenException(String message, String token, Throwable cause) {
        super(message, cause);
        this.token = token;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " - Token : [" + token + "]";
    }

    public String getToken() { return token; }
}
