package de.nak.ttmg.service;

import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.util.ValidationException;

import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This is the interface for the Room Service
 */
public interface RoomService {

    Long createRoom(Room room) throws ValidationException;

    List<Room> listRooms(String building, String roomNbr, RoomType type, Integer minSeats, Date freeBegin, Date freeEnd) throws ValidationException;

    Room loadRoom(Long id) throws ValidationException;

    void deleteRoom(Long id, Boolean force) throws ValidationException;
}
