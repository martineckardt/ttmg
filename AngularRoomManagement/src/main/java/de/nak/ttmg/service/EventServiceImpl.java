package de.nak.ttmg.service;

import de.nak.ttmg.dao.CourseDAO;
import de.nak.ttmg.dao.EventDAO;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.*;
import de.nak.ttmg.validator.CourseValidator;
import de.nak.ttmg.validator.TimeValidator;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 04/11/15.
 * The Service Implementation for Events
 */
public class EventServiceImpl implements EventService {

    @Inject
    private EventDAO eventDAO;

    @Inject
    private CourseDAO courseDAO;

    private final TimeValidator timeValidator = new TimeValidator();

    private final CourseValidator courseValidator = new CourseValidator();

    @Override
    public List<Event> listEvents(Long centuriaId, Long tutorId, Long roomId, Long courseId, Date rangeStart, Date rangeEnd) throws ValidationException {
        final Date end;
        if (rangeStart != null && rangeEnd == null) {
            // If no end date is specified, create a range with a length of one week to create valid time tables.
            end = new Date(rangeStart.getTime() + 7*24*60*60);
        } else {
            end = rangeEnd;
        }
        //Create date range for the filter
        DateRange range = DateRangeFactory.createDateRange(rangeStart, end);
        //Search for events
        return eventDAO.listEvents(centuriaId, tutorId, roomId, courseId, range);
    }

    @Override
    public List<Event> createEvents(List<Event> events, Long courseId, boolean force) throws ValidationException {
        //Load course of the event
        Course course = courseDAO.load(courseId);
        //Set / overwrite the course of the event
        events.forEach(event1 -> {
            course.addEvent(event1);
            //Validate, if the new event causes any
            if (!force) {
                timeValidator.validateEvent(event1);
            }
        });

        //Validate course with the new event
        courseValidator.validateCourse(course, force);
        //Create the events in the backend
        return eventDAO.createEvents(events);
    }

    @Override
    public Event updateEvent(Long id, Long courseId, Event event, boolean force) throws ValidationException {
        Event oldEvent = loadEvent(id);
        if (event != null && event.getId() != null && event.getId().equals(id) && oldEvent.getId().equals(id)) {
            //Update the properties that may have changed
            oldEvent.setRooms(event.getRooms());
            oldEvent.setBegin(event.getBegin());
            oldEvent.setEnd(event.getEnd());

            //Check if the new event causes any conflicts
            if (!force) {
                timeValidator.validateEvent(oldEvent);
            }
            //Save updates to DB
            return eventDAO.update(oldEvent);
        } else {
            throw new InvalidParameterException("eventId", InvalidParameterException.InvalidParameterType.INCONSISTENT);
        }
    }

    @Override
    public Event loadEvent(Long id) throws ValidationException {
        return eventDAO.load(id);
    }

    @Override
    public void deleteEvent(Long id) throws ValidationException {
        Event event = loadEvent(id);
        //Remove the event from all rooms
        event.getRooms().forEach(room1 -> room1.getEvents().remove(event));
        //Remove the event from the course
        event.getCourse().getEvents().remove(event);
        //Delete the event in the DB
        eventDAO.delete(event);
    }
}
