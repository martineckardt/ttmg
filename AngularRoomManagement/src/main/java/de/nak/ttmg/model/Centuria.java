package de.nak.ttmg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
public class Centuria implements Participant {
    private Character letter;

    private StudyProgram program;

    private Integer nbrOfStudents;

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public Integer getChangeTime() {
        return null;
    }

    @Override
    public Integer getRequiredSeats() {
        return nbrOfStudents;
    }
}
