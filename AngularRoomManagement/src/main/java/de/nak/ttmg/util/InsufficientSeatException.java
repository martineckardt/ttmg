package de.nak.ttmg.util;

/**
 * Created by felixb on 30/10/15.
 * This exception will be thrown if a course has more participants than the room can handle
 */
public class InsufficientSeatException extends IgnorableValidationException {
    private final int seats;

    public InsufficientSeatException(int missingSeats) {
        super("There are " + missingSeats + " seats missing.");
        seats = missingSeats;
    }

    public int getSeats() {
        return seats;
    }
}
