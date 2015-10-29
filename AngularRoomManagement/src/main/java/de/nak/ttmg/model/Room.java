package de.nak.ttmg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felixb on 27/10/15.
 */
@Entity
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = {"BUILDING", "ROOM_NUMBER"}))
public class Room implements Serializable, HasAvailability {

    /**
     * The unique identifier.
     */
    private Long id;

    /**
     * The building.
     */
    private String building;

    /**
     * The room's number.
     */
    private String roomNumber;

    /**
     * Number of seats in the room.
     */
    private Integer seats;

    private RoomType type;

    private Set<Course> courses = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Column(name = "room_number", nullable = false)
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Column(nullable = false)
    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="course_room", joinColumns=@JoinColumn(name="room_id"), inverseJoinColumns=@JoinColumn(name="course_id"))
    @JsonBackReference
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + building + roomNumber + '\'' +
                ", type=" + type +
                ", seats=" + seats +
                ", id=" + id +
                '}';
    }

    @Transient
    @Override
    public Integer getCustomChangeTime() {
        return type.getDefaultChangeTime();
    }
}
