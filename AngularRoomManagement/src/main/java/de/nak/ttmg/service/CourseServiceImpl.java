package de.nak.ttmg.service;

import de.nak.ttmg.dao.CourseDAO;
import de.nak.ttmg.model.Course;
import de.nak.ttmg.util.CourseValidator;
import de.nak.ttmg.util.TimeValidatior;
import de.nak.ttmg.util.ValidationException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 */
public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO;
    private TimeValidatior timeValidatior = new TimeValidatior();
    private CourseValidator courseValidator = new CourseValidator();

    @Override
    public Long createCourse(Course course, boolean force) throws ValidationException {
        courseValidator.validateCourse(course, force);
        if (!force) {
            timeValidatior.validateTime(course);
        }
        return courseDAO.create(course);
    }

    @Override
    public Boolean updateCourse(Course course, boolean force) throws ValidationException {
        courseValidator.validateCourse(course, force);
        if (!force) {
            timeValidatior.validateTime(course);
        }
        courseDAO.update(course);
        return true;
    }

    @Override
    public List<Course> listCourses() {
        //TODO
        return courseDAO.findAll();
    }

    @Override
    public Course loadCourse(Long id) throws ValidationException {
        return courseDAO.load(id);
    }

    @Override
    public Boolean deleteCourse(Long id) throws ValidationException{
        Course course = loadCourse(id);
        courseDAO.delete(course);
        return true;
    }

    @Inject
    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }
}
