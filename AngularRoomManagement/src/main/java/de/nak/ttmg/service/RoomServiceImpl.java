package de.nak.ttmg.service;

import de.nak.ttmg.dao.RoomDAO;
import de.nak.ttmg.model.DateRange;
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

    @Inject
    private RoomDAO roomDAO;
    private final TimeValidator timeValidator = new TimeValidator();
    private final RoomValidator roomValidator = new RoomValidator();
    private final DateRangeValidator rangeValidator = new DateRangeValidator();

    @Override
    public List<Room> listRooms(String building, String roomNbr, RoomType type, Integer minSeats, Date start, Date end,
                                Integer rangeRepeat) throws ValidationException{
        DateRange freeRange = rangeValidator.createValidRange(start, end);
        rangeValidator.validateRepeatCount(rangeRepeat);

        List<Room> allRooms = roomDAO.findAll(building, roomNbr, type, minSeats);
        if (freeRange != null) {
            if (rangeRepeat == null) {
                rangeRepeat = 0;
            }
            for (int i = 0; i<rangeRepeat; i++) {
                DateRange range = new DateRange(freeRange, i);
                allRooms.stream().filter(room -> timeValidator.hasTime(room, range));
            }
        } else if (rangeRepeat == null) {
            throw new InvalidParameterException("rangeRepeat",
                    InvalidParameterException.InvalidParameterType.INCONSISTENT);
        }
        return allRooms;
    }

    @Override
    public Room loadRoom(Long id) {
        Room room = roomDAO.load(id);
        if (room == null) {
            throw new EntityNotFoundException("room", id);
        }
        return room;
    }

    @Override
    public Room createRoom(Room room) throws ValidationException {
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

    @Override
    public void deleteRoom(Long id, Boolean force) throws ValidationException {
        Room room = loadRoom(id);
        if (room == null) {
            throw new EntityNotFoundException("room", id);
        }
        if ((force == null || !force) && room.getEvents().size() > 0) {
            throw new IsBusyException(room);
        }
        roomDAO.delete(room);
    }
}
