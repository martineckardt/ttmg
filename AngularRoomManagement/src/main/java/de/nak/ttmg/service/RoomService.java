package de.nak.ttmg.service;

import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;

import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface RoomService {

    Long createRoom(Room room);

    List<Room> listRooms(String building, String roomNbr, RoomType type, Integer minSeats, Date freeBegin, Date freeEnd);

    Room loadRoom(Long id);
}
