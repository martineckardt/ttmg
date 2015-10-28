package de.nak.ttmg.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by felixb on 27/10/15.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"BUILDING", "ROOM_NUMBER"}))
public class Room implements Serializable {

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
