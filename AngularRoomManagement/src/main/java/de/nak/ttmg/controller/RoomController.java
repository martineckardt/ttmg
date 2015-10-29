package de.nak.ttmg.controller;

import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.pdf.PDFCreator;
import de.nak.ttmg.service.RoomService;
import de.nak.ttmg.util.EntityNotFoundException;
import de.nak.ttmg.util.ValidationException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 27/10/15.
 */
@RestController
public class RoomController {

    private RoomService roomService;

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public List<Room> listRooms(@RequestParam(required = false, value = "building") String building,
                                @RequestParam(required = false, value = "roomNbr") String roomNbr,
                                @RequestParam(required = false, value = "type") String roomTypeString,
                                @RequestParam(required = false, value = "minSeats") Integer minSeats,
                                @RequestParam(required = false, value = "freeStart") Date freeStart,
                                @RequestParam(required = false, value = "freeEnd") Date freeEnd
                                ) {
        RoomType roomType = RoomType.typeForString(roomTypeString);
        return roomService.listRooms(building, roomNbr, roomType, minSeats, freeStart, freeEnd);
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable final Long id) {
        return roomService.loadRoom(id);
    }

    @RequestMapping(value = "/rooms/{id}/pdf", method = RequestMethod.GET, produces="application/pdf")
    public InputStreamResource getTimeTablePDF(@PathVariable final Long id) {
        try {
            PDFCreator pdf = new PDFCreator();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Room room = getRoom(id);
            if (room != null) {
                pdf.createPDF(stream, room);
            } else {
                pdf.createErrorPDF(stream, "Room with id " + id + " could not be found!");
            }

            stream.flush();
            byte[] pdfContent = stream.toByteArray();
            InputStream inputStream = new ByteArrayInputStream(pdfContent);
            return new InputStreamResource(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public Long createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @Inject
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
