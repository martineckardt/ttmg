package de.nak.ttmg.model;

/**
 * Created by felixb on 28/10/15.
 */
public enum RoomType {
    LECUTRE(0, "Lecture", 15), COMPUTER(1, "Computer", 15), LAB(2, "Lab", 15), AUDIMAX(3, "Audimax", 30);


    private int identifier;
    private String displayName;
    private Integer defaultChangeTime;

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
}
