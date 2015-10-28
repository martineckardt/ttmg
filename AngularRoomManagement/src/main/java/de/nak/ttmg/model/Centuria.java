package de.nak.ttmg.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"LETTER", "PROGRAM", "YEAR"}))
public class Centuria implements Serializable, Participant {
    private Character letter;

    private StudyProgram program;

    private Integer nbrOfStudents;

    private Integer year;

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

    @Column(nullable = false, name = "letter")
    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    @Column(nullable = false, name = "program")
    @Enumerated(EnumType.STRING)
    public StudyProgram getProgram() {
        return program;
    }

    public void setProgram(StudyProgram program) {
        this.program = program;
    }

    @Column(name = "nbr_of_students", nullable = false)
    public Integer getNbrOfStudents() {
        return nbrOfStudents;
    }

    public void setNbrOfStudents(Integer nbrOfStudents) {
        this.nbrOfStudents = nbrOfStudents;
    }

    @Column(name = "year", nullable = false)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
