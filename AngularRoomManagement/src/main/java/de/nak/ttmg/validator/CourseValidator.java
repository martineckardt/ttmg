package de.nak.ttmg.validator;

import de.nak.ttmg.model.*;
import de.nak.ttmg.exceptions.InsufficientSeatException;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;

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
        validateEvents(course.getEvents(),course.getParticipants(), force);
        validateTutor(course.getTutor());
        validateName(course.getName());
        validateParticipants(course.getParticipants());
    }

    private void validateEvents(Set<Event> events, Set<Centuria> centurias, boolean force) throws ValidationException {
        if (events != null) {
            boolean validDates = events.stream().allMatch(event -> event.getBegin().before(event.getEnd()));
            if (!validDates) {
                throw new InvalidParameterException("events", InvalidParameterException.InvalidParameterType.INCONSISTENT);
            }
            events.forEach(event -> validateCapacity(event.getRooms(), centurias, force));
        }
    }

    private void validateCapacity(Set<Room> rooms, Set<Centuria> centurias, boolean force) throws ValidationException {
        if (rooms == null) {
            throw new InvalidParameterException("rooms", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
        if (rooms.isEmpty()) {
            //Events with no rooms are always ok
            return;
        }
        Integer capacity = rooms.stream().mapToInt(Room::getSeats).sum();
        Integer required = centurias.stream().mapToInt(Centuria::getNbrOfStudents).sum();
        if (required > capacity && !force) {
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
        boolean validCharacters = name.matches("[a-zA-Z0-9öäüÖÄÜ][. 'a-zA-Z0-9öäüÖÄÜ-]*");
        if (!validCharacters) {
            throw new InvalidParameterException("name", InvalidParameterException.InvalidParameterType.INVALID_FORMAT);
        }
    }

    private void validateParticipants(Set<Centuria> centurias) throws ValidationException {
        if (centurias == null) {
            throw new InvalidParameterException("participants", InvalidParameterException.InvalidParameterType.INVALID_NULL);
        }
    }
}
