package de.nak.ttmg.service;

import de.nak.ttmg.dao.RoomDAO;
import de.nak.ttmg.exceptions.*;
import de.nak.ttmg.model.DateRange;
import de.nak.ttmg.model.DateRangeFactory;
import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.validator.RoomValidator;
import de.nak.ttmg.validator.TimeValidator;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * The Service Implementation for Rooms
 */
public class RoomServiceImpl implements RoomService {

    @Inject
    private RoomDAO roomDAO;
    private final TimeValidator timeValidator = new TimeValidator();
    private final RoomValidator roomValidator = new RoomValidator();

    @Override
    public List<Room> listRooms(String building, String roomNbr, RoomType type, Integer minSeats, Date start, Date end,
                                Integer rangeRepeat) throws ValidationException {
        DateRange freeRange = DateRangeFactory.createDateRange(start, end);
        List<Room> allRooms = roomDAO.findAll(building, roomNbr, type, minSeats);
        if (freeRange != null) {
            if (rangeRepeat == null) {
                rangeRepeat = 0;
            }
            for (int i = 0; i <= rangeRepeat; i++) {
                System.out.println("Check time for room");
                DateRange range = DateRangeFactory.createDateRangeWithOffset(freeRange, rangeRepeat);
                allRooms.stream().filter(room -> {
                    boolean hasTIme = timeValidator.hasTime(room, range);
                    System.out.println("has Time; room = " + room + " true/false: " + hasTIme);
                    return hasTIme;
                });
            }
        } else {
            System.out.println("Request free rooms: " + start + " end: " + end);
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
            throw new IsBusyException(room, room.getEvents().size());
        }
        roomDAO.delete(room);
    }
}
