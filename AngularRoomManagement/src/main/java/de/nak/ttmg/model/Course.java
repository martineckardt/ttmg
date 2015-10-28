package de.nak.ttmg.model;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by felixb on 28/10/15.
 */
@Entity
public class Course {

    private EventType type;

    private Set<Event> events;

    private Set<Room> rooms;

    private Set<Participant> participants;

    private Tutor tutor;

    private String name;
}
