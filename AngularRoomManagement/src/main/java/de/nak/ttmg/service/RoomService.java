package de.nak.ttmg.service;

import de.nak.ttmg.model.Room;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface RoomService {

    void createRoom(Room room);

    List<Room> listRooms();

    Room loadRoom(Long id);
}
