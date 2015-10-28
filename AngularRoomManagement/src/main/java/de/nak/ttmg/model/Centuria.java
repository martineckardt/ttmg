package de.nak.ttmg.model;

import javax.persistence.Entity;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
public class Centuria implements Participant {
    private Character letter;

    private StudyProgram program;

    private Integer nbrOfStudents;


    @Override
    public Integer getChangeTime() {
        return null;
    }

    @Override
    public Integer getRequiredSeats() {
        return nbrOfStudents;
    }
}
