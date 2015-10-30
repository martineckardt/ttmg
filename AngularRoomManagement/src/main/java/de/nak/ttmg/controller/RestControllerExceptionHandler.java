package de.nak.ttmg.controller;

import de.nak.ttmg.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by felixb on 29/10/15.
 * This Class handles all Exceptions occurring on the server and ensures,
 * that all error information is transferred to the client via JSON.
 */
@ControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(TimeConflictException.class)
    @ResponseBody
    TimeConflictException handleTCException(TimeConflictException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseBody
    InvalidParameterException handleIPException(InvalidParameterException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.ALREADY_REPORTED) //208??
    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseBody
    EntityAlreadyExistsException handleEAEException(EntityAlreadyExistsException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    EntityNotFoundException handleENFException(EntityNotFoundException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(DateRangeException.class)
    @ResponseBody
    DateRangeException handleENFException(DateRangeException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(InsufficientSeatException.class)
    @ResponseBody
    InsufficientSeatException handleISException(InsufficientSeatException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    ValidationException handleGeneralException(ValidationException e) {
        return e;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    ValidationException handleAllOtherServerErrors(RuntimeException e) {
        return new ValidationException(e);
    }
}
