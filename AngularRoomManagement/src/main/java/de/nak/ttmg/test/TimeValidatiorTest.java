package de.nak.ttmg.test;

import de.nak.ttmg.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValidateTime() throws Exception {



    }
}
