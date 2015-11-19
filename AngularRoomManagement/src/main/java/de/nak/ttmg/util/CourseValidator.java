package de.nak.ttmg.util;

import de.nak.ttmg.model.*;

import java.util.Set;

/**
 * Created by felixb on 29/10/15.
 * This class checks if a course is valid.
 */
public class CourseValidator {

    /**
     * Validates if a course has valid properties only.
     * @param course to be tested
     * @param force if validation should be less strict
     * @throws ValidationException
     */
    public void validateCourse(Course course, boolean force) throws ValidationException {
        validateEvents(course.getEvents(),course.getParticipants(), course.getType(), force);
        validateTutor(course.getTutor());
        validateName(course.getName());
    }

    private void validateEvents(Set<Event> events, Set<Centuria> centurias, EventType type, boolean force) throws ValidationException {
        if (events != null) {
            boolean validDates = events.stream().allMatch(event -> event.getBegin().before(event.getEnd()));
            if (!validDates) {
                throw new InvalidParameterException("events", InvalidParameterException.InvalidParameterType.INCONSISTENT);
            }
        }
        events.forEach(event -> validateRooms(event.getRooms(), centurias, type, force));
    }

    private void validateRooms(Set<Room> rooms, Set<Centuria> centurias, EventType type, boolean force) throws ValidationException {
        if (rooms == null || rooms.isEmpty()) {
            throw new InvalidParameterException("rooms", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        Integer capacity = rooms.stream().mapToInt(Room::getSeats).sum();
        Integer required = centurias.stream().mapToInt(Centuria::getNbrOfStudents).sum();
        if (type == EventType.SEMINAR) {
            //Limit required seats to 20
            required = Math.min(required, 20);
        }
        if (required > capacity && force) {
            throw new InsufficientSeatException(capacity-required);
        }
    }

    private void validateTutor(Tutor tutor) throws ValidationException {
        if (tutor == null) {
            throw new InvalidParameterException("tutor", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
    }

    private void validateName(String name) throws ValidationException {
        if (name == null || name.isEmpty()) {
            throw new InvalidParameterException("name", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        boolean validCharacters = name.chars().allMatch(Character::isLetterOrDigit);
        if (!validCharacters) {
            throw new InvalidParameterException("name", InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
    }

}
