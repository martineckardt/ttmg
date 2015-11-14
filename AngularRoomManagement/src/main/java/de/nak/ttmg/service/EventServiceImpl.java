package de.nak.ttmg.service;

import de.nak.ttmg.dao.EventDAO;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.util.DateRangeValidator;
import de.nak.ttmg.util.TimeValidator;
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

    @Override
    public List<Event> listEvents(Long centuriaId, Long tutorId, Long roomId, Date rangeStart, Date rangeEnd) throws ValidationException {
        final Date end;
        if (rangeStart != null && rangeEnd == null) {
            // TODO Documentation
            end = new Date(rangeStart.getTime() + 7*24*60*60);
        } else {
            end = rangeEnd;
        }
        DateRangeValidator.validateDateRange(rangeStart, end);
        return eventDAO.listEvents(centuriaId,tutorId,roomId, rangeStart, rangeEnd);
    }
}
