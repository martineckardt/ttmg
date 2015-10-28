package de.nak.ttmg.service;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.Tutor;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface EventService {

    Long createEvent (Event event);

    void updateEvent(Event event);

    List<Event> listEvents();

    //List<Event> findEventsWithTutor(Tutor tutor);

    //List<Event> findEventsWithCenturia(Centuria centuria);

    //List<Event> findEventsWithRoom(Room room);

    Event loadEvent(Long id);

    void deleteEvent(Long id);
}
