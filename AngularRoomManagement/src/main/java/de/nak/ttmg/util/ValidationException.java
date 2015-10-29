package de.nak.ttmg.util;

import org.hibernate.HibernateException;

/**
 * Created by felixb on 28/10/15.
 */
public class ValidationException extends HibernateException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable t) {
        super("Server Exception: " + t.getClass().toString());
    }
}
