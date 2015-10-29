package de.nak.ttmg.test;

import de.nak.ttmg.model.*;
import de.nak.ttmg.util.TimeConflictException;
import de.nak.ttmg.util.TimeValidatior;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by felixb on 28/10/15.
 */
public class TimeValidatiorTest {

    private Room roomA101;
    private Room roomA102;
    private Room roomA103;
    private Course courseIAA;
    private Tutor tutor;
    private Centuria centuria;

    private Date pastStart = new Date(2014, 5, 3);
    private Date pastEnd = new Date(2014,7,10);
    private Date futureStart = new Date(2015, 11, 15);
    private Date futureEnd = new Date(2016, 6,20);

    private Event past;
    private Event future;

    private TimeValidatior validator = new TimeValidatior();

    @Before
    public void setUp() throws Exception {

        roomA101 = new Room();
        roomA101.setBuilding("A");
        roomA101.setSeats(30);
        roomA101.setRoomNumber("101");
        roomA101.setType(RoomType.LECUTRE);
        roomA102 = new Room();
        roomA102.setBuilding("A");
        roomA102.setSeats(30);
        roomA102.setRoomNumber("102");
        roomA102.setType(RoomType.COMPUTER);
        roomA103 = new Room();
        roomA103.setBuilding("A");
        roomA103.setSeats(30);
        roomA103.setRoomNumber("103");
        roomA103.setType(RoomType.LECUTRE);

        tutor = new Tutor();
        tutor.setChangeTime(20);
        tutor.setFirstName("Stephan");
        tutor.setLastName("Anft");
        tutor.setTitle("");

        centuria = new Centuria();
        centuria.setChangeTime(10);
        centuria.setLetter("a".charAt(0));
        centuria.setProgram(StudyProgram.WINF);
        centuria.setNbrOfStudents(29);
        centuria.setYear(2012);

        Set<Room> rooms = new HashSet<>(2);
        rooms.add(roomA101);
        rooms.add(roomA102);
        Set<Centuria> participants = new HashSet<>(1);
        participants.add(centuria);

        courseIAA = new Course();
        courseIAA.setName("IAA");

        courseIAA.setRooms(rooms);
        courseIAA.setTutor(tutor);
        courseIAA.setParticipants(participants);
        courseIAA.setType(EventType.COURSE);
        centuria.getCourses().add(courseIAA);
        roomA101.getCourses().add(courseIAA);
        roomA102.getCourses().add(courseIAA);
        tutor.getCourses().add(courseIAA);

        past = new Event();
        past.setBegin(pastStart);
        past.setEnd(pastEnd);
        past.setCourse(courseIAA);

        future = new Event();
        future.setBegin(futureStart);
        future.setEnd(futureEnd);
        future.setCourse(courseIAA);

        Set<Event> events = new HashSet<>(2);
        events.add(past);
        events.add(future);
        courseIAA.setEvents(events);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValidateTime() throws Exception {
    assertNotNull(tutor);
        assertNotNull(courseIAA);
        assertNotNull(centuria);
        assertNotNull(roomA101);
        assertNotNull(roomA102);
        assertNotNull(roomA103);
        assertNotNull(courseIAA.getParticipants());
        assertNotNull(centuria.getCourses());
        assertTrue(centuria.getCourses().size() > 0);
        assertTrue(roomA101.getCourses().size() > 0);
        assertTrue(roomA102.getCourses().size() > 0);
        assertTrue(roomA103.getCourses().size() == 0);
        assertTrue(tutor.getCourses().size() > 0);

        try {
            validator.validateTime(courseIAA);
        } catch (TimeConflictException e) {
            assertTrue(false);
        }

        Course copy = new Course();
        Set<Event> events = new HashSet<>(2);
        events.add(past);
        events.add(future);
        copy.setEvents(events);
        copy.setType(EventType.COURSE);
        copy.setTutor(tutor);
        Set<Room> rooms = new HashSet<>(2);
        rooms.add(roomA101);
        rooms.add(roomA102);
        Set<Centuria> participants = new HashSet<>(1);
        participants.add(centuria);
        copy.setRooms(rooms);
        copy.setParticipants(participants);
        copy.setName("Copy");
        try {
            validator.validateTime(copy);
            assertTrue(false);
        } catch (TimeConflictException e) {
            System.out.println("e.getFailures() = " + e.getFailures());
        }
    }
}
