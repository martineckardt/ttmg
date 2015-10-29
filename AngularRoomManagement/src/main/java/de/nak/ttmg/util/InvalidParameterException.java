package de.nak.ttmg.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by felixb on 28/10/15.
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Test Bla Bla Fooooo")
public class InvalidParameterException extends ValidationException {

    public enum InvalidParameterType{
        INVALID_FORMAT("has an invalid format / type."),
        INVALID_RANGE("has a value beyond its bounds."),
        INVALID_LENGTH("has an invalid"),
        INVALID_NULL("must not be null"),
        INVALID_NOT_NULL("must be null"),
        INCONSISTENT("is inconsistent with object");

        private String debugText;

        InvalidParameterType(String debugText) {
            this.debugText = debugText;
        }

        public String getDebugText() {
            return debugText;
        }
    }

    private String parameterName;
    private InvalidParameterType type;

    public InvalidParameterException(String parameterName, InvalidParameterType type) {
        super("The parameter " + parameterName + " " + type.getDebugText());
        this.type = type;
    }
}
