package de.nak.ttmg.validator;

import de.nak.ttmg.exceptions.TimeConflictException;
import de.nak.ttmg.model.Course;
import de.nak.ttmg.model.DateRange;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.model.HasAvailability;

/**
 * Created by Martin Eckardt on 27.11.2015.
 */
public interface TimeValidator {
    void validateTime(Course course) throws TimeConflictException;

    void validateEvent(Event event) throws TimeConflictException;

    boolean hasTime(HasAvailability object, DateRange range, Event ignore);
}
