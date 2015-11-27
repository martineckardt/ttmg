package de.nak.ttmg.service;

import de.nak.ttmg.dao.CourseDAO;
import de.nak.ttmg.exceptions.EntityAlreadyExistsException;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.Course;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.validator.CourseValidator;
import de.nak.ttmg.validator.TimeValidator;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 * The Service Implementation for Courses
 */
public class CourseServiceImpl implements CourseService {

    @Inject
    private CourseDAO courseDAO;

    @Inject
    private TimeValidator timeValidator;

    @Inject
    private CourseValidator courseValidator;

    @Override
    public Course createCourse(Course course, boolean force) throws ValidationException {
        if (course.getName() != null) {
            //Trim name
            course.setName(course.getName().trim());
        }
        //Validated the course
        courseValidator.validateCourse(course, force);
        if (!force) {
            timeValidator.validateTime(course);
        }
        //Create course in db
        if (course.getId() == null) {
            try {
                return courseDAO.create(course);
            } catch (ConstraintViolationException e) {
                throw new EntityAlreadyExistsException();
            } catch (Exception e) {
                throw new ValidationException(e);
            }
        } else {
            throw new InvalidParameterException("courseId", InvalidParameterException.InvalidParameterType.INVALID_NOT_NULL);
        }
    }

    @Override
    public Course updateCourse(Long id, Course course, boolean force) throws ValidationException {
        if (course != null && course.getId() != null && course.getId().equals(id)) {
            //Load old course from backend and update the properties that may have changed.
            Course oldCourse = loadCourse(id);
            if (course.getName() != null) {
                oldCourse.setName(course.getName().trim());
            }
            oldCourse.setTutor(course.getTutor());
            oldCourse.setParticipants(course.getParticipants());
            oldCourse.setType(course.getType());
            //Validate the updated course
            courseValidator.validateCourse(oldCourse, force);
            if (!force) {
                timeValidator.validateTime(oldCourse);
            }
            //Save changes to backend
            return courseDAO.update(oldCourse);
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
        return courseDAO.load(id);
    }

    @Override
    public void deleteCourse(Long id) throws ValidationException {
        Course course = loadCourse(id);
        //Remove the course from the centurias
        for (Centuria centuria : course.getParticipants()) {
            centuria.getCourses().remove(course);
        }
        //Remove the course from the tutor
        course.getTutor().getCourses().remove(course);
        //Delete all events
        for (Event event : course.getEvents()) {
            event.getRooms().forEach(room -> room.getEvents().remove(event));
        }
        //Delete course in DB
        courseDAO.delete(course);
    }
}
