package de.nak.ttmg.service;

import de.nak.ttmg.model.Event;
import de.nak.ttmg.util.ValidationException;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 04/11/15.
 */
public interface EventService {

    List<Event> listEvents(Long centuriaId, Long tutorId, Long roomId, Date rangeStart, Date rangeEnd) throws ValidationException;
}
