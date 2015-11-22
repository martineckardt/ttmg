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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "course_id", nullable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Column(name = "rooms")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name="event_room", joinColumns=@JoinColumn(name="event_id"), inverseJoinColumns=@JoinColumn(name="room_id"))
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (course != null ? !course.equals(event.course) : event.course != null) return false;
        if (rooms != null ? !rooms.equals(event.rooms) : event.rooms != null) return false;
        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (begin != null ? !begin.equals(event.begin) : event.begin != null) return false;
        return !(end != null ? !end.equals(event.end) : event.end != null);
    }

    public boolean equalsId(Event o) {
        if (o != null && id != null && o.id != null) {
            return id.equals(o.id);
        }
        return equals(o);
    }

    @Override
    public int hashCode() {
        int result = course != null ? course.hashCode() : 0;
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (begin != null ? begin.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
