package de.nak.ttmg.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private Long id;

    private EventType type;

    private Set<Event> events = new HashSet<>();

    private Set<Room> rooms = new HashSet<>();

    private Set<Centuria> participants = new HashSet<>();

    private Tutor tutor;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Column(name = "events")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Column(name = "rooms")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Column(name = "participants")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    public Set<Centuria> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Centuria> participants) {
        this.participants = participants;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tutor_id", nullable = false)
    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "type=" + type +
                ", events=" + events +
                ", rooms=" + rooms +
                ", participants=" + participants +
                ", tutor=" + tutor +
                ", name='" + name + '\'' +
                '}';
    }
}
