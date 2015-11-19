package de.nak.ttmg.service;

import de.nak.ttmg.dao.CourseDAO;
import de.nak.ttmg.model.Course;
import de.nak.ttmg.util.*;

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
        System.out.println("Create Course in CourseServiceImpl");
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
        //TODO
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
        courseDAO.delete(loadCourse(id));
    }
}
