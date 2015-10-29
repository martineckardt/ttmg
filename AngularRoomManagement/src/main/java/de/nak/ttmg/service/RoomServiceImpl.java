package de.nak.ttmg.service;

import de.nak.ttmg.dao.RoomDAO;
import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.util.*;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class RoomServiceImpl implements RoomService {
    private RoomDAO roomDAO;
    private TimeValidator validator = new TimeValidator();
    private RoomValidator roomValidator = new RoomValidator();

    @Override
    public List<Room> listRooms(String building, String roomNbr, RoomType type, Integer minSeats, Date freeBegin, Date freeEnd) throws ValidationException{
        DateRangeValidator.validateDateRange(freeBegin, freeEnd);
        List<Room> allRooms = roomDAO.findAll(building, roomNbr, type, minSeats);
        if (freeBegin != null && freeEnd != null) {
            allRooms.stream().filter(room -> validator.hasTime(room, freeBegin, freeEnd));
        }
        return allRooms;
    }

    @Override
    public Room loadRoom(Long id) {
        return roomDAO.load(id);
    }

    @Override
    public Long createRoom(Room room) throws ValidationException {
        roomValidator.validateRoom(room);
        if (room.getId() == null) {
            try {
                return roomDAO.create(room);
            } catch (Exception e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    throw new EntityAlreadyExistsException();
                }
                throw new ValidationException(e.getCause());
            }
        } else {
            throw new InvalidParameterException("roomId", InvalidParameterException.InvalidParameterType.INVALID_NOT_NULL);
        }
    }

    @Inject
    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
}
