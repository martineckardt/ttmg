package de.nak.ttmg.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.nak.ttmg.util.Constants;

/**
 * Created by felixb on 28/10/15.
 * This exception is thrown if parameter are invalid or not available
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

        private final String debugText;

        InvalidParameterType(String debugText) {
            this.debugText = debugText;
        }

        public String getDebugText() {
            return debugText;
        }
    }

    private final String parameterName;
    private final InvalidParameterType type;

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

    @Override
    public String getLocalizableMessage() {
        return Constants.INVALID_PARAMETER;
    }
}
