package de.nak.ttmg.controller;

import de.nak.ttmg.model.Room;
import de.nak.ttmg.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by felixb on 27/10/15.
 */
@RestController
public class RoomController {

    private RoomService roomService;

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public List<Room> listRooms() {
        return roomService.listRooms();
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET)
    public Room getRoom(@PathVariable Long id) throws Exception {
        return roomService.loadRoom(id);
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public void createRoom(@RequestBody Room room) {
        roomService.createRoom(room);
    }

    @Inject
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
