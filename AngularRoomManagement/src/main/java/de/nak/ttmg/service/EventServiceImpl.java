package de.nak.ttmg.service;

import de.nak.ttmg.dao.EventDAO;
import de.nak.ttmg.model.Event;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 */
public class EventServiceImpl implements EventService {

    private EventDAO eventDAO;

    @Override
    public void createEvent(Event event) {
        eventDAO.create(event);
    }

    @Override
    public void updateEvent(Event event) {
        //todo

    }

    @Override
    public List<Event> listEvents() {
        return eventDAO.findAll();
    }

    @Override
    public Event loadEvent(Long id) {
        return eventDAO.load(id);
    }

    @Override
    public void deleteEvent(Event event) {
        eventDAO.delete(event);

    }

    @Inject
    public void setEventDAO (EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }
}
