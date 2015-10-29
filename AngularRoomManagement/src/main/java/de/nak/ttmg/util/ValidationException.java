package de.nak.ttmg.util;

/**
 * Created by felixb on 28/10/15.
 */
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Exception e) {
        super("Server Exception: " + e.getClass().toString());
    }
}
