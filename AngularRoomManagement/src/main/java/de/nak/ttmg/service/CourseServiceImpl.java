package de.nak.ttmg.service;

import de.nak.ttmg.dao.CourseDAO;
import de.nak.ttmg.model.Course;
import de.nak.ttmg.model.Event;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by U519647 on 28.10.2015.
 */
public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO;

    @Override
    public Long createCourse(Course course) {
        return courseDAO.create(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseDAO.update(course);
    }

    @Override
    public List<Course> listCourses() {
        return courseDAO.findAll();
    }

    @Override
    public Course loadCourse(Long id) {
        return courseDAO.load(id);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = loadCourse(id);
        courseDAO.delete(course);
    }

    @Inject
    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }
}
