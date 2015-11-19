package de.nak.ttmg.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.HibernateException;

/**
 * Created by felixb on 28/10/15.
 * This is a super class for all validation exceptions.
 * This exception can also be thrown for all other internal server errors.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ValidationException extends HibernateException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable t) {
        super("Server Exception: " + t.getClass().toString() + " message: " + t.getMessage());
        t.printStackTrace();
    }

    @Override
    @JsonIgnore //We don't want stack traces in client
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    public String getExceptionType() {
        return this.getClass().toString();
    }

    public Boolean getIgnorable() {
        return false;
    }
}
