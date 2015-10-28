package de.nak.ttmg.model;

import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 */
public interface HasAvailability {
    Integer getCustomChangeTime();

    Set<Course> getCourses();
}
