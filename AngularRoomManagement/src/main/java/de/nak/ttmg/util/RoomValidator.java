package de.nak.ttmg.util;

import de.nak.ttmg.model.Room;

/**
 * Created by felixb on 28/10/15.
 */
public class RoomValidator {

    public void validateRoom(Room room) throws InvalidParameterException {
        validateBuilding(room.getBuilding());
        validateRoomNbr(room.getRoomNumber());
        validateSeats(room.getSeats());
    }

    private void validateBuilding(String building) throws InvalidParameterException {
        if (building == null) {
            throw new InvalidParameterException("building", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (building.trim().length() != 1) {
            throw new InvalidParameterException("building", InvalidParameterException.InvalidParameterType.INVALID_LENGTH);
        }
        char b = building.toCharArray()[0];
        if (!Character.isLetter(b)) {
            throw new InvalidParameterException("building", InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
    }

    private void validateRoomNbr(String roomNbr) throws InvalidParameterException {
        if (roomNbr == null) {
            throw new InvalidParameterException("roomNbr", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        roomNbr = roomNbr.trim();
        if (roomNbr.length() < 1 || roomNbr.length() > 3) {
            throw new InvalidParameterException("roomNbr", InvalidParameterException.InvalidParameterType.INVALID_LENGTH);
        }
    }

    private void validateSeats(Integer seats) throws InvalidParameterException {
        if (seats == null) {
            throw new InvalidParameterException("seats", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (seats < 0) {
            throw new InvalidParameterException("seats", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }
}
