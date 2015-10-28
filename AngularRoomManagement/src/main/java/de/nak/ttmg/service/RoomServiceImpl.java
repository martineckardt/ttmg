package de.nak.ttmg.service;

import de.nak.ttmg.dao.RoomDAO;
import de.nak.ttmg.model.Room;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class RoomServiceImpl implements RoomService {
    private RoomDAO roomDAO;

    @Override
    public List<Room> listRooms() {
        return roomDAO.findAll();
    }

    @Override
    public Room loadRoom(Long id) {
        return roomDAO.load(id);
    }

    @Override
    public void createRoom(Room room) {
        roomDAO.create(room);
    }

    @Inject
    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
}
