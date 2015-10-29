package de.nak.ttmg.controller;

import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.service.RoomService;
import de.nak.ttmg.util.ServerResponse;
import de.nak.ttmg.util.ValidationException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 27/10/15.
 */
@RestController
public class RoomController {

    private RoomService roomService;

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public ServerResponse<List<Room>> listRooms(@RequestParam(required = false, value = "building") String building,
                                @RequestParam(required = false, value = "roomNbr") String roomNbr,
                                @RequestParam(required = false, value = "type") String roomTypeString,
                                @RequestParam(required = false, value = "minSeats") Integer minSeats,
                                @RequestParam(required = false, value = "freeStart") Date freeStart,
                                @RequestParam(required = false, value = "freeEnd") Date freeEnd
                                ) {
        return new ServerResponse<>(()-> {
                RoomType roomType = RoomType.typeForString(roomTypeString);
                return roomService.listRooms(building, roomNbr, roomType, minSeats, freeStart, freeEnd);});
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
    public ServerResponse<Room> getRoom(@PathVariable final Long id) {
        return new ServerResponse<>(()-> roomService.loadRoom(id));
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public ServerResponse<Long> createRoom(@RequestBody Room room) {
        return new ServerResponse<>(()-> roomService.createRoom(room));
    }

    @Inject
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
