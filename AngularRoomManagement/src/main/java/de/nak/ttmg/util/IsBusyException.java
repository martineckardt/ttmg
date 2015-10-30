package de.nak.ttmg.util;

import de.nak.ttmg.model.HasAvailability;

/**
 * Created by felixb on 30/10/15.
 * This Exception is thrown if a HasAvailability object cannot be modified / deleted
 * because it has some existing courses that might get broken.
 */
public class IsBusyException extends IgnorableValidationException {
    private Integer courseCount;

    public IsBusyException(HasAvailability object) {
        super("Cannot delte " + object.getObjectType() + "; there are still " + object.getCourses().size() + " referenced courses!");
        this.courseCount = object.getCourses().size();
    }

    public Integer getCourseCount() {
        return courseCount;
    }
}
