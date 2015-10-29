package de.nak.ttmg.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.HibernateException;

/**
 * Created by felixb on 28/10/15.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ValidationException extends HibernateException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable t) {
        super("Server Exception: " + t.getClass().toString());
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        //We don't want stack traces in client
        return null;
    }

    public String getExceptionType() {
        return this.getClass().toString();
    }
}
