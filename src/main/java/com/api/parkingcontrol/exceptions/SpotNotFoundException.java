package com.api.parkingcontrol.exceptions;

public class SpotNotFoundException extends Exception{

    private String message;

    public SpotNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
