package de.nak.ttmg.controller;

import de.nak.ttmg.model.Event;
import de.nak.ttmg.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 04/11/15.
 */
@RestController
public class EventController {
    private EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<Event> listCourses(@RequestParam(required = false, value = "centuryId") Long centuriaId,
                                    @RequestParam(required = false, value = "tutorId") Long tutorId,
                                    @RequestParam(required = false, value = "roomId") Long roomId,
                                   @RequestParam(required = false, value = "courseId") Long courseId,
                                    @RequestParam(required = false, value = "rangeStart") Date rangeStart,
                                    @RequestParam(required = false, value = "rangeEnd") Date rangeEnd
    ) {
        return eventService.listEvents(centuriaId, tutorId, roomId, courseId, rangeStart, rangeEnd);
    }

    @Inject
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
