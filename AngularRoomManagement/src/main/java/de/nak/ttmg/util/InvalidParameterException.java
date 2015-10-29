package de.nak.ttmg.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by felixb on 28/10/15.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class InvalidParameterException extends ValidationException {

    public enum InvalidParameterType {
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
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }

    public InvalidParameterType getType() {
        return type;
    }
}
