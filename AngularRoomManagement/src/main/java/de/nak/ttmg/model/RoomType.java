package de.nak.ttmg.model;

/**
 * Created by felixb on 28/10/15.
 * This enum contains all room types
 */
public enum RoomType {
    LECTURE("Lecture", 0), COMPUTER("Computer", 15), LAB("Lab", 0), AUDIMAX("Audimax", 0);

    private final String displayName;

    private final Integer defaultChangeTime;

    RoomType(String displayName, Integer defaultChangeTime) {
        this.defaultChangeTime = defaultChangeTime;
        this.displayName = displayName;
    }

    public Integer getDefaultChangeTime() {
        return defaultChangeTime;
    }

    public static RoomType typeForString(String toParse) {
        if (toParse != null) {
            for (RoomType t : RoomType.values()) {
                if (t.displayName.toLowerCase().equals(toParse.toLowerCase())) {
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
