package de.nak.ttmg.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.service.EventService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 27/10/15.
 */
@RestController
public class EventController {

    private EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> listEvents(@RequestParam(required = false, value = "centuriaId") Long centuriaId,
                                  @RequestParam(required = false, value = "tutorId") Long tutorId,
                                  @RequestParam(required = false, value = "roomId") Long roomId,
                                  @RequestParam(required = false, value = "rangeStart") Date rangeStart,
                                  @RequestParam(required = false, value = "rangeEnd") Date rangeEnd) {

        return eventService.listEvents();
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable Long id) {
        return eventService.loadEvent(id);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public Long createEvent(@RequestBody Event event,
                            @RequestParam(required = false, value = "force") Boolean force) {
        return eventService.createEvent(event);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
    public void saveEvent(@PathVariable Long id,
                          @RequestBody Event event,
                          @RequestParam(required = false, value = "force") Boolean force) {
        if (event != null && event.getId() != null && event.getId().equals(id)) {
            eventService.updateEvent(event);
        }
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
