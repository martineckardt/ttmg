package de.nak.ttmg.controller;

import de.nak.ttmg.util.ExceptionInfo;
import de.nak.ttmg.util.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import sun.security.validator.ValidatorException;

/**
 * Created by felixb on 29/10/15.
 */
@ControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    Exception handleException(Exception e) {
        System.out.println("Catched Exception: " + e);
        return e;
    }
}
