package de.nak.ttmg.util;

import de.nak.ttmg.model.Event;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class TimeConflictException extends ValidationException {
    List<Event> failures;

    public TimeConflictException(List<Event> failures) {
        super("There are " + failures.size() + " conflicting events.");
        this.failures = failures;
    }

    public List<Event> getFailures() {
        return failures;
    }
}
