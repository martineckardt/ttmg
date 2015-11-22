package de.nak.ttmg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.nak.ttmg.util.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 * This class is the centuria entity
 */
@Entity
@Table(name = "centuria", uniqueConstraints = @UniqueConstraint(columnNames = {"LETTER", "PROGRAM", "YEAR"}))
public class Centuria implements Serializable, HasAvailability {
    private static final Integer defaultChangeTime = 15;

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

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "participants")
    @JsonBackReference
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    /**
     * This is used by hibernate only.
     * When querying for the change time refer to
     * {@link #getCustomChangeTime()}
     * @return change time or null if not set
     */
    @Column(name = "change_time")
    @JsonIgnore
    public Integer getChangeTime() {
        return changeTime;
    }

    @JsonProperty //We only want to set the change time
    public void setChangeTime(Integer changeTime) {
        this.changeTime = changeTime;
    }

    @Transient
    @JsonIgnore
    @Override
    public Set<Event> getEvents() {
        Set<Event> events = new HashSet<>();
        for (Course c : getCourses()) {
            events.addAll(c.getEvents());
        }
        return events;
    }

    @Override
    public String toString() {
        return "Centuria{" +
                program + (year%100) + letter +
                ", nbrOfStudents=" + nbrOfStudents +
                ", changeTime=" + changeTime +
                '}';
    }

    @Transient
    @Override
    public String getReadableString() {
        return "" + program.getLetter() + (year%100) + letter;
    }

    @Transient
    @Override
    public String getObjectType() {
        return Constants.CENTURIA;
    }

    @Transient
    @Override
    public Integer getCustomChangeTime() {
        if (getChangeTime() != null) {
            return getChangeTime();
        }
        return defaultChangeTime;
    }
}
