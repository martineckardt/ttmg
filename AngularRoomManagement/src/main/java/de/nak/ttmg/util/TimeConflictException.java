package de.nak.ttmg.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.nak.ttmg.model.Event;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This exception is thrown if a time conflict exists between to courses
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class TimeConflictException extends ValidationException {
    private final List<Event> failures;

    public TimeConflictException(List<Event> failures) {
        super("There are " + failures.size() + " conflicting events.");
        this.failures = failures;
    }

    public List<Event> getFailures() {
        return failures;
    }
}
