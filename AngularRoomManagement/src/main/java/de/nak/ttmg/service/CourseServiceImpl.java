package de.nak.ttmg.service;

import de.nak.ttmg.dao.CourseDAO;
import de.nak.ttmg.exceptions.EntityNotFoundException;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.Course;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.validator.CourseValidator;
import de.nak.ttmg.validator.TimeValidator;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 * The Service Implementation for Courses
 */
public class CourseServiceImpl implements CourseService {

    @Inject
    private CourseDAO courseDAO;
    private final TimeValidator timeValidator = new TimeValidator();
    private final CourseValidator courseValidator = new CourseValidator();

    @Override
    public Course createCourse(Course course, Boolean force) throws ValidationException {
        if (force == null) {
            force = false;
        }
        if (course.getName() != null) {
            course.setName(course.getName().trim());
        }
        courseValidator.validateCourse(course, force);
        if (!force) {
            timeValidator.validateTime(course);
        }
        return courseDAO.create(course);
    }

    @Override
    public Course updateCourse(Long id, Course course, Boolean force) throws ValidationException {
        if (force == null) {
            force = false;
        }
        if (course != null && course.getId() != null && course.getId().equals(id)) {
            if (course.getName() != null) {
                course.setName(course.getName().trim());
            }
            courseValidator.validateCourse(course, force);
            if (!force) {
                timeValidator.validateTime(course);
            }
            return courseDAO.update(course);
        } else {
            throw new InvalidParameterException("courseId", InvalidParameterException.InvalidParameterType.INCONSISTENT);
        }
    }

    @Override
    public List<Course> listCourses() {
        return courseDAO.findAll();
    }

    @Override
    public Course loadCourse(Long id) throws ValidationException {
        Course course = courseDAO.load(id);
        if (course == null) {
            throw new EntityNotFoundException("course", id);
        }
        return course;
    }

    @Override
    public void deleteCourse(Long id) throws ValidationException {
        Course course = loadCourse(id);
        for (Centuria centuria : course.getParticipants()) {
            centuria.getCourses().remove(course);
        }
        course.getTutor().getCourses().remove(course);
        for (Event event : course.getEvents()) {
            event.getRooms().forEach(room -> room.getEvents().remove(event));
        }
        courseDAO.delete(course);
    }
}
