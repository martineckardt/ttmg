package de.nak.ttmg.model;

/**
 * Created by felixb on 27/10/15.
 * This enum lists all types of events
 */
public enum CourseType {
    COURSE("Course", 15), EXAM("Exam", 30), SEMINAR("Seminar", 15), ELECTIVE_MODULE("Elective module", 15);

    private final int minChangeTime;

    private final String displayName;

    CourseType(String displayName, int minChangeTime) {
        this.displayName = displayName;
        this.minChangeTime = minChangeTime;
    }

    public int getMinChangeTime() {
        return minChangeTime;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
