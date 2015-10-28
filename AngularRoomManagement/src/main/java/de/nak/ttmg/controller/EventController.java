package de.nak.ttmg.controller;

import de.nak.ttmg.model.Event;
import de.nak.ttmg.service.EventService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by felixb on 27/10/15.
 */
@RestController
public class EventController {

    private EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> listEvents() {
        return eventService.listEvents();
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable Long id) {
        return eventService.loadEvent(id);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public Long createEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
    public Long saveEvent(@PathVariable Long id, @RequestBody Event event) {
        if (event != null && event.getId() != null && event.getId().equals(id)) {
            return eventService.saveEvent(event);
        }
        return null;
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @Inject
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

}
