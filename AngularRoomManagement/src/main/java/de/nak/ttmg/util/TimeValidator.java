package de.nak.ttmg.util;

import de.nak.ttmg.model.Course;
import de.nak.ttmg.model.DateRange;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.model.HasAvailability;

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
        List<HasAvailability> objectsToTest = new ArrayList<>();
        objectsToTest.add(course.getTutor());
        objectsToTest.addAll(course.getParticipants());

        List<Event> failures = new ArrayList<>();
        course.getEvents().forEach(event -> {
            objectsToTest.addAll(event.getRooms());
            for (HasAvailability object : objectsToTest) {
                try {
                    validateTime(object,event);
                }
                catch (TimeConflictException e) {
                    failures.addAll(e.getFailures());
                }
            }
        });
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
    public void validateTime(HasAvailability object, Event event) throws TimeConflictException {
        List<Event> failures = new ArrayList<>();
            try {
                validateTime(object, event.getBegin(), event.getEnd(), event);
            }
            catch (TimeConflictException e) {
                failures.addAll(e.getFailures());
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
    public void validateTime(HasAvailability object, Date start, Date end, Event ignore) throws TimeConflictException {
        Integer changeTime = object.getCustomChangeTime();
        Course course = ignore.getCourse();
        if (ignore != null && course.getType().getMinChangeTime() > changeTime) {
            changeTime = course.getType().getMinChangeTime();
        }
        Date newStart = new Date(start.getTime() - changeTime * 60*1000);
        Date newEnd = new Date(end.getTime() + changeTime * 60*1000);
        checkAdjustedTime(object,newStart,newEnd,ignore);
    }

    /**
     * Checks if an object has any conflicting events.
     * Instead of throwing an exception it is returning true if no conflicts exist, otherwise false
     * @param object to be validated
     * @param range time range to block
     * @return true if no conflicts exist, false otherwise
     */
    public boolean hasTime(HasAvailability object, DateRange range) {
        try {
            validateTime(object,range.getBegin(), range.getEnd(), null);
            return true;
        } catch (TimeConflictException e) {
            return false;
        }
    }

    private void checkAdjustedTime(HasAvailability object, Date start, Date end, Event ignore) throws TimeConflictException {
        List<Event> failures = new ArrayList<>();
        object.getEvents().stream().filter(event -> event != ignore).forEach(e -> {
                if (e.getBegin().after(start) && e.getBegin().before(end)) {
                    failures.add(e);
                } else if (e.getEnd().after(start) && e.getEnd().before(end)) {
                    failures.add(e);
                } else if (e.getBegin().equals(start) || e.getEnd().equals(end)) {
                    failures.add(e);
                }
        });
        if (!failures.isEmpty()) {
            throw new TimeConflictException(failures);
        }
    }
}
