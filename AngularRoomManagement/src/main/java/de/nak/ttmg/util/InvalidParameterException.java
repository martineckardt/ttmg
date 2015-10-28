package de.nak.ttmg.util;

/**
 * Created by felixb on 28/10/15.
 */
public class InvalidParameterException extends ValidationException {

    enum InvalidParameterType{
        INVALID_FORMAT("has an invalid format / type."),
        INVALID_RANGE("has a value beyond its bounds."),
        INVALID_LENGTH("has an invalid"),
        INVALID_NULL("must not be null");

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
        super("The parameter " + parameterName + type.getDebugText());
        this.type = type;
    }
}