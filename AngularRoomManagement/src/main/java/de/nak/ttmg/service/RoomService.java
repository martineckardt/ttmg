package de.nak.ttmg.service;

import de.nak.ttmg.model.Event;
import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.exceptions.ValidationException;

import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This is the interface for the Room Service
 */
public interface RoomService {

    Room createRoom(Room room) throws ValidationException;

    List<Room> listRooms(String building, String roomNbr, RoomType type, Integer minSeats, Date freeBegin, Date freeEnd,
             Long ignoreEventId) throws ValidationException;

    Room loadRoom(Long id) throws ValidationException;

    void deleteRoom(Long id, Boolean force) throws ValidationException;
}
