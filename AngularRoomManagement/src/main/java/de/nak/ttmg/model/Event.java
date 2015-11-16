package de.nak.ttmg.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felixb on 27/10/15.
 * This class is the event entity
 */
@Entity
@Table(name = "event")
public class Event implements Serializable, HasReadableString {

    private Course course;

    private Set<Room> rooms = new HashSet<>();

    private Long id;

    private Date begin;

    private Date end;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    @Column(nullable = false)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(name = "rooms")
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "events")
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Event{" +
                "begin=" + begin +
                ", end=" + end +
                '}';
    }

    @Transient
    @Override
    public String getReadableString() {
        DateRange range = new DateRange(getBegin(), getEnd());
        return range.getReadableString();
    }
}
