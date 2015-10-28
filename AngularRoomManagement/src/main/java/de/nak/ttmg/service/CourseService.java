package de.nak.ttmg.service;

import de.nak.ttmg.model.*;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface CourseService {

    Long createCourse(Course course);

    void updateCourse(Course course);

    List<Course> listCourses();

    Course loadCourse(Long id);

    void deleteCourse(Long id);
}
