package de.nak.ttmg.service;

import de.nak.ttmg.model.Event;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface EventService {

    Long saveEvent(Event event);

    List<Event> listEvents();

    Event loadEvent(Long id);

    void deleteEvent(Long id);
}
