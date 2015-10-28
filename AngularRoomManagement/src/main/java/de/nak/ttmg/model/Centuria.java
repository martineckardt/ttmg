package de.nak.ttmg.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
@Table(name = "centuria", uniqueConstraints = @UniqueConstraint(columnNames = {"LETTER", "PROGRAM", "YEAR"}))
public class Centuria implements Serializable {
    private Character letter;

    private StudyProgram program;

    private Integer nbrOfStudents;

    private Integer year;

    private Long id;

    private Set<Course> courses = new HashSet<>();

    private Integer changeTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "centuria_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="course_centuria", joinColumns=@JoinColumn(name="centuria_id"), inverseJoinColumns=@JoinColumn(name="course_id"))
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Column(name = "change_time")
    public Integer getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Integer changeTime) {
        this.changeTime = changeTime;
    }

    @Override
    public String toString() {
        return "Centuria{" +
                program + year + letter +
                ", nbrOfStudents=" + nbrOfStudents +
                ", changeTime=" + changeTime +
                '}';
    }
}
