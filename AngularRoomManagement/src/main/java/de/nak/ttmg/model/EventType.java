package de.nak.ttmg.model;

/**
 * Created by felixb on 27/10/15.
 */
public enum EventType {
    COURSE(0, "Course", 15), EXAM(1, "Exam", 30), SEMINAR(2, "Seminar", 15), WPK(3, "WPK", 15);

    private int minChangeTime;

    private int identifier;

    private String diplayName;

    /**
     * Creates a new EventType
     *
     * @param identifier
     * @param diplayName
     * @param minChangeTime
     */
    EventType(int identifier, String diplayName, int minChangeTime) {
        this.diplayName = diplayName;
        this.identifier = identifier;
        this.minChangeTime = minChangeTime;
    }

    public int getMinChangeTime() {
        return minChangeTime;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getDiplayName() {
        return diplayName;
    }

    @Override
    public String toString() {
        return getDiplayName();
    }
}
