package de.nak.ttmg.controller;

import de.nak.ttmg.model.Event;
import de.nak.ttmg.service.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by felixb on 04/11/15.
 * This RestController handles all requests regarding events.
 */
@RestController
public class EventController {
    @Inject
    private EventService eventService;

    /**
     * Requests all events
     * @param centuriaId to filter events to a centuria (optional)
     * @param tutorId to filter events to a tutor (optional)
     * @param roomId to filter events to a room (optional)
     * @param courseId to filter events to a course (optional)
     * @param rangeStart to filter events between a range (optional, only together with rangeEnd)
     * @param rangeEnd to filter events between a range (optional, only together with rangeStart)
     * @return list of events
     */
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> listEvents(@RequestParam(required = false, value = "centuriaId") Long centuriaId,
                                    @RequestParam(required = false, value = "tutorId") Long tutorId,
                                    @RequestParam(required = false, value = "roomId") Long roomId,
                                    @RequestParam(required = false, value = "courseId") Long courseId,
                                    @RequestParam(required = false, value = "rangeStart")
                                      @DateTimeFormat(pattern="yyyy-MM-dd_HH:mm") Date rangeStart,
                                    @RequestParam(required = false, value = "rangeEnd")
                                      @DateTimeFormat(pattern="yyyy-MM-dd_HH:mm") Date rangeEnd
    ) {
        return eventService.listEvents(centuriaId, tutorId, roomId, courseId, rangeStart, rangeEnd);
    }

    /**
     * Creates multiple events for a course
     * @param events to be created
     * @param courseId of the course the events belong to
     * @param force if true, time and capacity validation will be disabled
     * @return list of created courses with id of the db
     */
    @RequestMapping(value = "/courses/{courseId}/events", method = RequestMethod.POST)
    public List<Event> createEvents(@RequestBody Map<Integer,Event> events,
                             @PathVariable Long courseId,
                             @RequestParam(required = false, value = "force") Boolean force) {
        List<Event> eventList = new ArrayList<>();
        for (Event event : events.values()) {
            eventList.add(event);
        }
        return eventService.createEvents(eventList, courseId, force);
    }

    /**
     * Updates an event
     * @param id of the event
     * @param courseId of the course the event belongs to (must match with the event,
     *                 you cannot move an event to a different course)
     * @param event object with updated parameters
     * @param force if true, time and capacity validation will be disabled
     * @return updated event
     */
    @RequestMapping(value = "/courses/{courseId}/events/{id}", method = RequestMethod.PUT)
    public Event saveEvent(@PathVariable Long id,
                             @PathVariable Long courseId,
                             @RequestBody Event event,
                             @RequestParam(required = false, value = "force") Boolean force) {
        return eventService.updateEvent(id, courseId, event, force);
    }

    /**
     * Deletes an event with a given id
     * @param id of the event to be deleted
     */
    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
