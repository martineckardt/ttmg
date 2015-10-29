package de.nak.ttmg.util;

import de.nak.ttmg.model.*;

import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by felixb on 29/10/15.
 */
public class CourseValidator {

    public void validateCourse(Course course, boolean force) throws ValidationException {
        validateEvents(course.getEvents());
        validateTutor(course.getTutor());
        validateParticipants(course.getParticipants());
        validateRooms(course.getRooms(),course.getParticipants(),force);
        validateName(course.getName());
    }

    private void validateEvents(Set<Event> events) throws ValidationException {
        if (events == null || events.isEmpty()) {
            throw new InvalidParameterException("events", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        boolean validDates = events.stream().allMatch(event -> event.getBegin().before(event.getEnd()));
        if (!validDates) {
            throw new InvalidParameterException("events", InvalidParameterException.InvalidParameterType.INCONSISTENT);
        }
    }

    private void validateRooms(Set<Room> rooms, Set<Centuria> centurias, boolean force) throws ValidationException {
        if (rooms == null || rooms.isEmpty()) {
            throw new InvalidParameterException("rooms", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        Integer capacity = rooms.stream().mapToInt(Room::getSeats).sum();
        Integer required = centurias.stream().mapToInt(Centuria::getNbrOfStudents).sum();
        if (required > capacity && force) {
            throw new InvalidParameterException("seats", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }

    private void validateTutor(Tutor tutor) throws ValidationException {
        if (tutor == null) {
            throw new InvalidParameterException("tutor", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
    }

    private void validateParticipants(Set<Centuria> centurias) throws ValidationException {
        if (centurias == null || centurias.isEmpty()) {
            throw new InvalidParameterException("participants", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        Integer participants = centurias.stream().mapToInt(Centuria::getNbrOfStudents).sum();
        if (participants <=0) {
            throw new InvalidParameterException("participants", InvalidParameterException.InvalidParameterType.INVALID_RANGE);
        }
    }

    private void validateName(String name) throws ValidationException {
        if (name == null || name.isEmpty()) {
            throw new InvalidParameterException("name", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        boolean validCharacters = name.chars().allMatch(x -> Character.isLetterOrDigit(x));
        if (!validCharacters) {
            throw new InvalidParameterException("name", InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
    }

}
