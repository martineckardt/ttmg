package de.nak.ttmg.service;

import de.nak.ttmg.dao.EventDAO;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.util.DateRangeValidator;
import de.nak.ttmg.util.ValidationException;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 04/11/15.
 */
public class EventServiceImpl implements EventService {

    @Inject
    private EventDAO eventDAO;

    private final DateRangeValidator rangeValidator = new DateRangeValidator();

    @Override
    public List<Event> listEvents(Long centuriaId, Long tutorId, Long roomId, Long courseId, Date rangeStart, Date rangeEnd) throws ValidationException {
        final Date end;
        if (rangeStart != null && rangeEnd == null) {
            // If no end date is specified, create a range with a length of one week to create valid time tables.
            end = new Date(rangeStart.getTime() + 7*24*60*60);
        } else {
            end = rangeEnd;
        }
        rangeValidator.validateRange(rangeStart, end);
        return eventDAO.listEvents(centuriaId, tutorId, roomId, courseId, rangeStart, rangeEnd);
    }
}
