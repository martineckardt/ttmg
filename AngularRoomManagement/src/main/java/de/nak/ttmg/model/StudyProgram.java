package de.nak.ttmg.model;

/**
 * Created by felixb on 27/10/15.
 */
public enum StudyProgram {
    AINF("AINF"), BWL("BWL"), WINF("WINF"), WING("WING");

    private String displayName;

    StudyProgram(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
