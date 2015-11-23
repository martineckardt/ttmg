package de.nak.ttmg.service;

import de.nak.ttmg.model.Event;
import de.nak.ttmg.exceptions.ValidationException;

import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 04/11/15.
 * This is the interface for the Event Service
 */
public interface EventService {

    List<Event> listEvents(Long centuriaId, Long tutorId, Long roomId, Long courseId, Date rangeStart,
                           Date rangeEnd) throws ValidationException;

    List<Event> createEvents(List<Event> events, Long courseId, boolean force) throws ValidationException;

    Event updateEvent(Long id, Long courseId, Event event, boolean force) throws ValidationException;

    Event loadEvent(Long id) throws ValidationException;

    void deleteEvent(Long id) throws ValidationException;
}
