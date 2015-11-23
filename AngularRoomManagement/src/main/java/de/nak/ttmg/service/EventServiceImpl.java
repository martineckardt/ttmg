package de.nak.ttmg.service;

import de.nak.ttmg.dao.CourseDAO;
import de.nak.ttmg.dao.EventDAO;
import de.nak.ttmg.exceptions.EntityNotFoundException;
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
        DateRange range = DateRangeFactory.createDateRange(rangeStart, end);
        return eventDAO.listEvents(centuriaId, tutorId, roomId, courseId, range);
    }

    @Override
    public List<Event> createEvents(List<Event> events, Long courseId, Boolean force) throws ValidationException {
        Course course = courseDAO.load(courseId);
        for (Event event : events) {
            event.setCourse(course);
            if (force == null) {
                force = false;
            }
            courseValidator.validateCourse(course, force);
            if (!force) {
                timeValidator.validateTime(course);
            }
        }
        return eventDAO.createEvents(events);
    }

    @Override
    public Event updateEvent(Long id, Long courseId, Event event, Boolean force) throws ValidationException {
        if (force == null) {
            force = false;
        }
        Event oldEvent = loadEvent(id);
        if (oldEvent == null) {
            throw new InvalidParameterException("id of event", InvalidParameterException.InvalidParameterType.INCONSISTENT);
        }
        if (event != null && event.getId() != null && event.getId().equals(id) && oldEvent.getId().equals(id)) {
            Course course = oldEvent.getCourse();
            if (!courseId.equals(course.getId())) {
                throw new InvalidParameterException("courseId", InvalidParameterException.InvalidParameterType.INCONSISTENT);
            }
            if (!force) {
                timeValidator.validateTime(course);
            }
            return eventDAO.update(event);
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
        for (Room room : event.getRooms()) {
            room.getEvents().remove(event);
        }
        event.getCourse().getEvents().remove(event);
        eventDAO.delete(event);
    }
}
