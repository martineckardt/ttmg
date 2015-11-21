package de.nak.ttmg.exceptions;

import de.nak.ttmg.model.HasAvailability;
import de.nak.ttmg.util.Constants;

/**
 * Created by felixb on 30/10/15.
 * This Exception is thrown if a HasAvailability object cannot be modified / deleted
 * because it has some existing courses that might get broken.
 */
public class IsBusyException extends IgnorableValidationException {
    private final Integer eventCount;

    public IsBusyException(HasAvailability object, Integer eventCount) {
        super("Cannot delete " + object.getObjectType() + "; there are still " + eventCount + " referenced events!");
        this.eventCount = eventCount;
    }

    public Integer getEventCount() {
        return eventCount;
    }

    @Override
    public String getLocalizableMessage() {
        return Constants.OBJECT_HAS_EVENTS;
    }
}
