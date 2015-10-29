package de.nak.ttmg.model;

/**
 * Created by felixb on 27/10/15.
 * This enum lists all types of events
 */
public enum EventType {
    COURSE("Course", 15), EXAM("Exam", 30), SEMINAR("Seminar", 15), WPK("WPK", 15);

    private final int minChangeTime;

    private final String displayName;

    EventType(String displayName, int minChangeTime) {
        this.displayName = displayName;
        this.minChangeTime = minChangeTime;
    }

    public int getMinChangeTime() {
        return minChangeTime;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
