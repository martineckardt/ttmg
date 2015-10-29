package de.nak.ttmg.model;

/**
 * Created by felixb on 27/10/15.
 */
public enum EventType {
    COURSE(0, "Course", 15), EXAM(1, "Exam", 30), SEMINAR(2, "Seminar", 15), WPK(3, "WPK", 15);

    private final int minChangeTime;

    private final int identifier;

    private final String displayName;

    /**
     * Creates a new EventType
     *
     * @param identifier
     * @param displayName
     * @param minChangeTime
     */
    EventType(int identifier, String displayName, int minChangeTime) {
        this.displayName = displayName;
        this.identifier = identifier;
        this.minChangeTime = minChangeTime;
    }

    public int getMinChangeTime() {
        return minChangeTime;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
