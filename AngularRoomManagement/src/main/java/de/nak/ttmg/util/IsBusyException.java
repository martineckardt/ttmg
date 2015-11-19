package de.nak.ttmg.util;

import de.nak.ttmg.model.HasAvailability;

/**
 * Created by felixb on 30/10/15.
 * This Exception is thrown if a HasAvailability object cannot be modified / deleted
 * because it has some existing courses that might get broken.
 */
public class IsBusyException extends IgnorableValidationException {
    private final Integer eventCount;

    public IsBusyException(HasAvailability object) {
        super("Cannot delete " + object.getObjectType() + "; there are still " + object.getEvents().size() + " referenced events!");
        this.eventCount = object.getEvents().size();
    }

    public Integer getEventCount() {
        return eventCount;
    }

    @Override
    public String getLocalizableMessage() {
        return Constants.OBJECT_HAS_EVENTS;
    }
}
