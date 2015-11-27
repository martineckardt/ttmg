package de.nak.ttmg.validator;

import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.model.Room;

/**
 * Created by Martin Eckardt on 27.11.2015.
 */
public interface RoomValidator {
    void validateRoom(Room room) throws InvalidParameterException;
}
