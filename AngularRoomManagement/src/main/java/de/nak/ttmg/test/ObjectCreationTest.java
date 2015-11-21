package de.nak.ttmg.test;

import de.nak.ttmg.model.*;
import de.nak.ttmg.util.DateRangeException;
import de.nak.ttmg.util.TimeConflictException;
import de.nak.ttmg.util.TimeValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by felixb on 28/10/15.
 * This Test class tests creation of several data objects.
 */
public class ObjectCreationTest {

    private Room roomA101;
    private Room roomA102;
    private Room roomA103;
    private Course courseIAA;
    private Course exam;
    private Tutor tutor;
    private Tutor tutor2;
    private Centuria centuria;
    private Centuria centuria2;

    @SuppressWarnings("deprecation")
    private final Date pastStart = new Date(2014, 5, 3);
    @SuppressWarnings("deprecation")
    private final Date pastEnd = new Date(2014,7,10);
    @SuppressWarnings("deprecation")
    private final Date futureStart = new Date(2015, 11, 15);
    @SuppressWarnings("deprecation")
    private final Date futureEnd = new Date(2016, 6,20);

    private Event past;
    private Event future;

    private final TimeValidator validator = new TimeValidator();

    @Before
    public void setUp() {

        roomA101 = new Room();
        roomA101.setBuilding("A".charAt(0));
        roomA101.setSeats(30);
        roomA101.setRoomNumber(101);
        roomA101.setType(RoomType.LECTURE);
        roomA102 = new Room();
        roomA102.setBuilding("B".charAt(0));
        roomA102.setSeats(30);
        roomA102.setRoomNumber(102);
        roomA102.setType(RoomType.COMPUTER);
        roomA103 = new Room();
        roomA103.setBuilding("C".charAt(0));
        roomA103.setSeats(29);
        roomA103.setRoomNumber(103);
        roomA103.setType(RoomType.LECTURE);

        tutor = new Tutor();
        tutor.setChangeTime(20);
        tutor.setFirstName("Stephan");
        tutor.setLastName("Anft");
        tutor.setTitle("");

        tutor2 = new Tutor();
        tutor2.setChangeTime(30);
        tutor2.setFirstName("Zimmermann");
        tutor2.setLastName("Frank");
        tutor2.setTitle("Prof. Dr.");

        centuria = new Centuria();
        centuria.setChangeTime(10);
        centuria.setLetter("a".charAt(0));
        centuria.setProgram(StudyProgram.WINF);
        centuria.setNbrOfStudents(29);
        centuria.setYear(2012);

        centuria2 = new Centuria();
        centuria2.setChangeTime(15);
        centuria2.setLetter("b".charAt(0));
        centuria2.setProgram(StudyProgram.WING);
        centuria2.setNbrOfStudents(26);
        centuria2.setYear(2013);

        Set<Room> rooms = new HashSet<>(2);
        rooms.add(roomA101);
        rooms.add(roomA102);
        Set<Centuria> participants = new HashSet<>(1);
        participants.add(centuria);

        courseIAA = new Course();
        courseIAA.setName("IAA");

        courseIAA.setTutor(tutor);
        courseIAA.setParticipants(participants);
        courseIAA.setType(CourseType.COURSE);
        centuria.getCourses().add(courseIAA);
        tutor.getCourses().add(courseIAA);

        exam = new Course();
        exam.setName("EXAM");
        exam.setType(CourseType.EXAM);
        exam.setParticipants(participants);
        centuria.getCourses().add(exam);
        courseIAA.setTutor(tutor);
        tutor.getCourses().add(exam);

        past = new Event();
        past.setBegin(pastStart);
        past.setEnd(pastEnd);
        past.setCourse(courseIAA);
        past.setRooms(rooms);

        future = new Event();
        future.setBegin(futureStart);
        future.setEnd(futureEnd);
        future.setCourse(courseIAA);
        future.setRooms(rooms);
        DateRange range = new DateRange(pastStart, pastEnd);
        assertNotNull(range);
        try {
            DateRange range2 = new DateRange(pastEnd, pastStart);
            assertNull(range2);
        } catch (DateRangeException e) {
            //All good!
        }


        Set<Event> events = new HashSet<>(2);
        events.add(past);
        events.add(future);
        courseIAA.setEvents(events);
        roomA101.getEvents().add(past);
        roomA101.getEvents().add(future);
        roomA102.getEvents().add(past);
        roomA102.getEvents().add(future);
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
        assertTrue(roomA101.getEvents().size() > 0);
        assertTrue(roomA102.getEvents().size() > 0);
        assertTrue(roomA103.getEvents().size() == 0);
        assertTrue(tutor.getCourses().size() > 0);

        try {
            validator.validateTime(courseIAA);
        } catch (TimeConflictException e) {
            assertTrue(false);
        }

        Course copy = new Course();
        Set<Event> events = new HashSet<>(2);
        Event e1 = new Event();
        e1.setBegin(futureStart);
        e1.setEnd(futureEnd);
        e1.setCourse(courseIAA);
        Event e2 = new Event();
        e2.setBegin(futureStart);
        e2.setEnd(futureEnd);
        e2.setCourse(courseIAA);

        events.add(e1);
        events.add(e2);
        copy.setEvents(events);
        copy.setType(CourseType.COURSE);
        copy.setTutor(tutor);
        Set<Room> rooms = new HashSet<>(2);
        rooms.add(roomA101);
        rooms.add(roomA102);
        e1.setRooms(rooms);
        e2.setRooms(rooms);
        Set<Centuria> participants = new HashSet<>(1);
        participants.add(centuria);
        copy.setParticipants(participants);
        copy.setName("Copy");
        try {
            validator.validateTime(copy);
            assertTrue(false);
        } catch (TimeConflictException e) {
            System.out.println("e.getFailures() = " + e.getFailures());
        }
    }

    @Test
    public void testEqual() throws Exception {
        assertNotEquals(roomA101, roomA102);
        assertNotEquals(roomA102, roomA103);
        assertNotEquals(roomA101, roomA103);

        assertNotEquals(courseIAA, exam);

        assertNotEquals(tutor,tutor2);
        assertNotEquals(centuria, centuria2);
        assertNotEquals(past, future);
    }
}
