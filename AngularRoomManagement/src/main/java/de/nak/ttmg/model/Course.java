package de.nak.ttmg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 * This class is the course entity
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private Long id;

    private CourseType type;

    private List<Event> events = new ArrayList<>();

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
    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    @Column(name = "events")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonBackReference
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
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

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (type != course.type) return false;
        if (events != null ? !events.equals(course.events) : course.events != null) return false;
        if (tutor != null ? !tutor.equals(course.tutor) : course.tutor != null) return false;
        return !(name != null ? !name.equals(course.name) : course.name != null);

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (events != null ? events.hashCode() : 0);
        result = 31 * result + (tutor != null ? tutor.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
