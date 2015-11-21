package de.nak.ttmg.controller;

import de.nak.ttmg.model.Event;
import de.nak.ttmg.service.CourseService;
import de.nak.ttmg.service.EventService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 04/11/15.
 * This RestController handles all requests regarding events.
 */
@RestController
public class EventController {
    @Inject
    private EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> listEvents(@RequestParam(required = false, value = "centuriaId") Long centuriaId,
                                    @RequestParam(required = false, value = "tutorId") Long tutorId,
                                    @RequestParam(required = false, value = "roomId") Long roomId,
                                    @RequestParam(required = false, value = "courseId") Long courseId,
                                    @RequestParam(required = false, value = "rangeStart") Date rangeStart,
                                    @RequestParam(required = false, value = "rangeEnd") Date rangeEnd
    ) {
        return eventService.listEvents(centuriaId, tutorId, roomId, courseId, rangeStart, rangeEnd);
    }

    @RequestMapping(value = "/courses/{courseId}/events", method = RequestMethod.POST)
    public List<Event> createEvents(@RequestBody List<Event> events,
                             @PathVariable Long courseId,
                             @RequestParam(required = false, value = "force") Boolean force) {
        return eventService.createEvents(events, courseId, force);
    }

    @RequestMapping(value = "/courses/{courseId}/events/{id}", method = RequestMethod.PUT)
    public Event saveEvent(@PathVariable Long id,
                             @PathVariable Long courseId,
                             @RequestBody Event event,
                             @RequestParam(required = false, value = "force") Boolean force) {
        return eventService.updateEvent(id, courseId, event, force);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
