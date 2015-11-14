package de.nak.ttmg.model;

/**
 * Created by felixb on 28/10/15.
 * This enum contains all room types
 */
public enum RoomType {
    LECTURE(0, "Lecture", 0), COMPUTER(1, "Computer", 15), LAB(2, "Lab", 0), AUDIMAX(3, "Audimax", 0);


    private final int identifier;
    private final String displayName;
    private final Integer defaultChangeTime;

    RoomType(int identifier, String displayName, Integer defaultChangeTime) {
        this.defaultChangeTime = defaultChangeTime;
        this.displayName = displayName;
        this.identifier = identifier;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Integer getDefaultChangeTime() {
        return defaultChangeTime;
    }

    public static RoomType typeForString(String toParse) {
        if (toParse != null) {
            for (RoomType t : RoomType.values()) {
                if (t.getDisplayName().toLowerCase().equals(toParse.toLowerCase())) {
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
