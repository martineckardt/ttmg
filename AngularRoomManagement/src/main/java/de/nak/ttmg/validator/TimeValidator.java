package de.nak.ttmg.validator;

import de.nak.ttmg.model.*;
import de.nak.ttmg.util.TimeConflict;
import de.nak.ttmg.exceptions.TimeConflictException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This class checks if an object is has overlapping events.
 */
public class TimeValidator {

    /**
     * Validates if a course has any conflicting events
     * @param course to validate
     * @throws TimeConflictException
     */
    public void validateTime(Course course) throws TimeConflictException {
        List<TimeConflict> failures = new ArrayList<>();
        //Iterate through all events
        course.getEvents().forEach(event -> {
            try {
                //Validate single event
                validateEvent(event);
            } catch (TimeConflictException e) {
                failures.addAll(e.getConflicts());
            }
        });
        //If conflicts exist, throw an exception
        if (!failures.isEmpty()) {
            throw new TimeConflictException(failures);
        }
    }

    /**
     * Validates a single event for time conflicts
     * @param event to be validated
     * @throws TimeConflictException
     */
    public void validateEvent(Event event) throws TimeConflictException {
        Course course = event.getCourse();
        List<TimeConflict> failures = new ArrayList<>();
        List<HasAvailability> objectsToTest = new ArrayList<>();
        //Collect all objects that may cause time conflicts
        objectsToTest.add(course.getTutor());
        objectsToTest.addAll(course.getParticipants());
        objectsToTest.addAll(event.getRooms());
        for (HasAvailability object : objectsToTest) {
            //Check for conflicts for a single event
            try {
                validateTime(object,event);
            }
            catch (TimeConflictException e) {
                failures.addAll(e.getConflicts());
            }
        }
        //If conflicts exist, throw an exception
        if (!failures.isEmpty()) {
            throw new TimeConflictException(failures);
        }
    }

    /**
     * Checks if an object has any conflicting events with a course
     * @param object to validate
     * @param event containing the events to test
     * @throws TimeConflictException
     */
    private void validateTime(HasAvailability object, Event event) throws TimeConflictException {
        List<TimeConflict> failures = new ArrayList<>();
            try {
                validateTime(object, event.getBegin(), event.getEnd(), event);
            }
            catch (TimeConflictException e) {
                failures.addAll(e.getConflicts());
            }
        if (!failures.isEmpty()) {
            throw new TimeConflictException(failures);
        }
    }

    /**
     * Checks if an object has any conflicting events
     * @param object to validate
     * @param start start time to block
     * @param end end time to block
     * @param ignore the event that can be ignored. (if the event is updated) Can be null.
     * @throws TimeConflictException
     */
    private void validateTime(HasAvailability object, Date start, Date end, Event ignore) throws TimeConflictException {
        Integer changeTime = object.getCustomChangeTime();
        //Calculate / adjust change time
        if (ignore != null && ignore.getCourse().getType().getMinChangeTime() > changeTime) {
            changeTime = ignore.getCourse().getType().getMinChangeTime();
        }
        //Add / Subtract change time for the given object
        Date newStart = new Date(start.getTime() - changeTime * 60*1000);
        Date newEnd = new Date(end.getTime() + changeTime * 60*1000);
        //Check adjusted time
        checkAdjustedTime(object,newStart,newEnd,ignore);
    }

    /**
     * Checks if an object has any conflicting events.
     * Instead of throwing an exception it is returning true if no conflicts exist, otherwise false
     * @param object to be validated
     * @param range time range to block
     * @param ignore to be ignored during validation (because it is the one being updated)
     * @return true if no conflicts exist, false otherwise
     */
    public boolean hasTime(HasAvailability object, DateRange range, Event ignore) {
        try {
            validateTime(object,range.getBegin(), range.getEnd(), ignore);
            return true;
        } catch (TimeConflictException e) {
            return false;
        }
    }

    /**
     * Checks for conflicts of an event with a time range. This time range includes the change time
     * @param object to be tested
     * @param start of the event minus the changetime
     * @param end of the event plus the changetime
     * @param ignore event to be excluded from validation
     * @throws TimeConflictException if a time conflict occurs.
     */
    private void checkAdjustedTime(HasAvailability object, Date start, Date end, Event ignore) throws TimeConflictException {
        List<TimeConflict> failures = new ArrayList<>();
        object.getEvents().stream().filter(event -> !event.equalsId(ignore)).forEach(e -> {
            //Check for conflicts, See documentation for details
            if (start.before(e.getEnd()) && end.after(e.getBegin())) {
                failures.add(new TimeConflict(e, object));
            } else if (e.getBegin().equals(start) || e.getEnd().equals(end)) {
                failures.add(new TimeConflict(e, object));
            }
        });
        //If conflicts exist, throw an exception
        if (!failures.isEmpty()) {
            throw new TimeConflictException(failures);
        }
    }
}
