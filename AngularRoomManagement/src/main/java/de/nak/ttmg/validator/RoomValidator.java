package de.nak.ttmg.validator;

import de.nak.ttmg.model.Room;
import de.nak.ttmg.exceptions.InvalidParameterException;

/**
 * Created by felixb on 28/10/15.
 * This class checks if a room is valid.
 */
public class RoomValidator {

    /**
     * Tests if a room is valid
     * @param room to be tested
     * @throws InvalidParameterException
     */
    public void validateRoom(Room room) throws InvalidParameterException {
        validateBuilding(room.getBuilding());
        validateRoomNbr(room.getRoomNumber());
        validateSeats(room.getSeats());
    }

    private void validateBuilding(Character building) throws InvalidParameterException {
        if (building == null) {
            throw new InvalidParameterException("building", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (!Character.isLetter(building)) {
            throw new InvalidParameterException("building", InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
    }

    private void validateRoomNbr(Integer roomNbr) throws InvalidParameterException {
        if (roomNbr == null) {
            throw new InvalidParameterException("roomNbr", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (roomNbr < 1 || roomNbr > 999) {
            throw new InvalidParameterException("roomNbr", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }

    private void validateSeats(Integer seats) throws InvalidParameterException {
        if (seats == null) {
            throw new InvalidParameterException("seats", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (seats < 0 || seats > 999) {
            throw new InvalidParameterException("seats", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }
}
