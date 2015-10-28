package de.nak.ttmg.service;

import de.nak.ttmg.dao.RoomDAO;
import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.util.DateRangeException;
import de.nak.ttmg.util.DateRangeValidator;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class RoomServiceImpl implements RoomService {
    private RoomDAO roomDAO;

    @Override
    public List<Room> listRooms(String building, String roomNbr, RoomType type, Integer minSeats, Date freeBegin, Date freeEnd) {
        //TODO Sebastian
        return roomDAO.findAll();
    }

    //try {
    //    DateRangeValidator.validateDateRange(freeStart, freeEnd);
    //} catch (DateRangeException e) {
    //    e.printStackTrace();
    //}

    @Override
    public Room loadRoom(Long id) {
        return roomDAO.load(id);
    }

    @Override
    public Long createRoom(Room room) {
        return roomDAO.create(room);
    }

    @Inject
    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
}
