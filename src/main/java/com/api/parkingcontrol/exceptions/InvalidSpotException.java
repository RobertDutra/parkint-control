package com.api.parkingcontrol.exceptions;

public class InvalidSpotException extends Exception{

    private String message;

    public InvalidSpotException(String message) {
        super(message);
        this.message = message;
    }
}
