package de.nak.ttmg.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.nak.ttmg.util.Constants;
import de.nak.ttmg.util.TimeConflict;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This exception is thrown if a time conflict exists between to courses
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class TimeConflictException extends IgnorableValidationException {
    private final List<TimeConflict> failures;

    public TimeConflictException(List<TimeConflict> failures) {
        super("There are " + failures.size() + " conflicting events.");
        this.failures = failures;
    }

    public List<TimeConflict> getFailures() {
        return failures;
    }

    @Override
    public String getLocalizableMessage() {
        return Constants.TIME_CONFLICTS;
    }
}
