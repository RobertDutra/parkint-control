package com.api.parkingcontrol.interceptor;

import com.api.parkingcontrol.exceptions.InvalidSpotException;
import com.api.parkingcontrol.exceptions.SpotNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {InvalidSpotException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public String deniedPermissionException(InvalidSpotException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String constraintViolationException(ConstraintViolationException ex) {
        return "Bad request";
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(Exception ex) {
        return "Internal error. Mensagem: " + ex.getMessage();
    }

    @ExceptionHandler(value = {SpotNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String deniedPermissionException(SpotNotFoundException ex) {
        return ex.getMessage();
    }
}