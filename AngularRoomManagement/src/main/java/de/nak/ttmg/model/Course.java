package de.nak.ttmg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 * This class is the course entity
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private Long id;

    private EventType type;

    private Set<Event> events = new HashSet<>();

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonBackReference
    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Column(name = "participants", nullable = false)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name="course_centuria", joinColumns=@JoinColumn(name="course_id"), inverseJoinColumns=@JoinColumn(name="centuria_id"))
    public Set<Centuria> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Centuria> participants) {
        this.participants = participants;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
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
                ", participants=" + participants +
                ", tutor=" + tutor +
                ", name='" + name + '\'' +
                '}';
    }

    @Transient
    public Integer getNumberOfStudents() {
        return participants.stream().mapToInt(Centuria::getNbrOfStudents).sum();
    }
}
