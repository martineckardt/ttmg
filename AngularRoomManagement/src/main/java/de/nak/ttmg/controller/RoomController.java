package de.nak.ttmg.controller;

import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.pdf.PDFCreator;
import de.nak.ttmg.service.RoomService;
import de.nak.ttmg.exceptions.ValidationException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 27/10/15.
 * This RestController handles all requests regarding rooms.
 */
@RestController
public class RoomController {

    @Inject
    private RoomService roomService;

    /**
     * Requests all rooms
     * @param building filter to this building (optional)
     * @param roomNbr filter to this roomNbr (optional)
     * @param roomTypeString filter to this roomType (optional)
     * @param minSeats filter to rooms with a greater capacity
     * @param freeStart only show rooms that are available in this time (optional, has to be set with freeEnd)
     * @param freeEnd only show rooms that are available in this time (optional, has to be set with freeStart)
     * @param eventId skip this event during validation (can be used when events are updated) (optional)
     * @return list of rooms matching all the criteria
     */
    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public List<Room> listRooms(@RequestParam(required = false, value = "building") String building,
                                @RequestParam(required = false, value = "roomNbr") String roomNbr,
                                @RequestParam(required = false, value = "type") String roomTypeString,
                                @RequestParam(required = false, value = "minSeats") Integer minSeats,
                                @RequestParam(required = false, value = "freeStart")
                                    @DateTimeFormat(pattern="yyyy-MM-dd_HH:mm") Date freeStart,
                                @RequestParam(required = false, value = "freeEnd")
                                    @DateTimeFormat(pattern="yyyy-MM-dd_HH:mm") Date freeEnd,
                                @RequestParam(required = false, value = "ignoreEventId") Long eventId
                                ) {
        RoomType roomType = RoomType.typeForString(roomTypeString);
        return roomService.listRooms(building, roomNbr, roomType, minSeats, freeStart, freeEnd, eventId);
    }

    /**
     * Requests a single room
     * @param id of the room
     * @return Room
     */
    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable final Long id) {
        return roomService.loadRoom(id);
    }

    /**
     * Creates a new room
     * @param room parameters
     * @return new room with id from db
     */
    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    /**
     * Creates a PDF time table for a given room
     * @param id of the room to create the time table for
     * @return PDF File
     */
    @RequestMapping(value = "/rooms/{id}/schedule.pdf", method = RequestMethod.GET, produces = "application/pdf")
    public InputStreamResource getTimeTablePDF(@PathVariable final Long id) {
        try {
            Room room;
            try {
                room = getRoom(id);
            } catch (ValidationException e) {
                room = null;
            }
            return PDFCreator.createPDF(room, "Room", id);
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream: " + ex.getMessage());
        }
    }

    /**
     * Deletes a room
     * @param id of the room to be deleted
     * @param force if true, validation is disabled (all events in this room will also be deleted)
     */
    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable Long id,
                                @RequestParam(required = false, value = "force") Boolean force) {
        roomService.deleteRoom(id, force);
    }
}
