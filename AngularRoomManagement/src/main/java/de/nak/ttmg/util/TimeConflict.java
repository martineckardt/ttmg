package de.nak.ttmg.util;

import de.nak.ttmg.model.Event;
import de.nak.ttmg.model.HasAvailability;

/**
 * Created by felixb on 19/11/15.
 * This object contains information about a Time Conflict
 */
public class TimeConflict {
    private final Event event;
    private final HasAvailability causer;

    public TimeConflict(Event event, HasAvailability causer) {
        this.event = event;
        this.causer = causer;
    }

    public Event getEvent() {
        return event;
    }

    public HasAvailability getCauser() {
        return causer;
    }
}
