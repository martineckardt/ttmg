package de.nak.ttmg.model;

import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 * This interface can be implemented by all objects that have courses that require checking for conflict.
 */
public interface HasAvailability extends HasReadableString {
    Integer getCustomChangeTime();

    Set<Course> getCourses();

    String getObjectType();
}
