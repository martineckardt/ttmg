package de.nak.ttmg.model;

/**
 * Created by felixb on 27/10/15.
 * This Enum contains all study programs
 */
public enum StudyProgram {
    AINF("AINF", "A".charAt(0)), BWL("BWL", "B".charAt(0)), WINF("WINF", "I".charAt(0)), WING("WING","W".charAt(0));

    private final String displayName;

    private final Character letter;

    StudyProgram(String displayName, Character letter) {
        this.displayName = displayName;
        this.letter = letter;
    }

    public static StudyProgram programForString(String toParse) {
        if (toParse != null && toParse.length() > 0) {
            Character character = toParse.toUpperCase().charAt(0);
                for (StudyProgram sp : StudyProgram.values()) {
                    if (sp.letter.equals(character)) {
                        return sp;
                    }
                    if (sp.displayName.toLowerCase().equals(toParse.toLowerCase())) {
                        return sp;
                    }
                }
        }
        return null;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getLetter() {
        return letter.toString();
    }
}
