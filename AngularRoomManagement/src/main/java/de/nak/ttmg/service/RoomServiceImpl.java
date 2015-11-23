package de.nak.ttmg.service;

import de.nak.ttmg.dao.EventDAO;
import de.nak.ttmg.dao.RoomDAO;
import de.nak.ttmg.exceptions.*;
import de.nak.ttmg.model.*;
import de.nak.ttmg.validator.RoomValidator;
import de.nak.ttmg.validator.TimeValidator;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * The Service Implementation for Rooms
 */
public class RoomServiceImpl implements RoomService {

    @Inject
    private RoomDAO roomDAO;

    @Inject
    private EventDAO eventDAO;

    private final TimeValidator timeValidator = new TimeValidator();
    private final RoomValidator roomValidator = new RoomValidator();

    @Override
    public List<Room> listRooms(String building, String roomNbr, RoomType type, Integer minSeats, Date start, Date end,
                                Long ignoreEventId)
            throws ValidationException {
        Event ignore = null;
        if (ignoreEventId != null) {
            ignore = eventDAO.load(ignoreEventId);
        }
        DateRange freeRange = DateRangeFactory.createDateRange(start, end);
        List<Room> allRooms = roomDAO.findAll(building, roomNbr, type, minSeats);
        List<Room> freeRooms = new ArrayList<>(allRooms.size());
        if (freeRange != null) {
            for (Room room : allRooms) {
                if (timeValidator.hasTime(room, freeRange, ignore)) {
                    freeRooms.add(room);
                }
            }
        } else {
            freeRooms = allRooms;
        }
        return freeRooms;
    }

    @Override
    public Room loadRoom(Long id) {
        return roomDAO.load(id);
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
            throw new IsBusyException(room, room.getEvents().size());
        }
        roomDAO.delete(room);
    }
}
