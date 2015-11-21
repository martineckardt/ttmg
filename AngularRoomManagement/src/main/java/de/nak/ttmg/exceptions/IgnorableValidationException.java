package de.nak.ttmg.exceptions;

/**
 * Created by felixb on 30/10/15.
 * This abstract Exception can be eliminated by forcing create/update/delete statement.
 */
public abstract class IgnorableValidationException extends ValidationException {

    IgnorableValidationException(String message) {
        super(message);
    }

    @Override
    public Boolean getIgnorable() {
        return true;
    }
}
