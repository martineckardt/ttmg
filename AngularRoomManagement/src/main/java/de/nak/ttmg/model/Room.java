package de.nak.ttmg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.nak.ttmg.util.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by felixb on 27/10/15.
 * This class is the room entity
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
    private Character building;

    /**
     * The room's number.
     */
    private Integer roomNumber;

    /**
     * Number of seats in the room.
     */
    private Integer seats;

    private RoomType type;

    private Set<Event> events = new HashSet<>();

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
    public Character getBuilding() {
        return building;
    }

    public void setBuilding(Character building) {
        this.building = building;
    }

    @Column(name = "room_number", nullable = false)
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
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

    @Column(name = "events", nullable = false)
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "rooms", cascade = CascadeType.ALL)
    @JsonBackReference
    public Set<Event> getEvents() {
        return events;
    }

    @Transient
    @Override
    public String getReadableString() {
        //Pad room number to 3 characters
        String room = String.format("%03d",roomNumber);
        return "" + building + room;
    }

    @Transient
    @Override
    public String getObjectType() {
        return Constants.ROOM;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
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

    /**
     * The "custom change time" cannot be customized. however, it is also called custom change time to
     * be consistent with centuria and tutor properties.
     * @return change time of the room type
     */
    @Transient
    @Override
    public Integer getCustomChangeTime() {
        return type.getDefaultChangeTime();
    }
}
