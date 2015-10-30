package de.nak.ttmg.service;

import de.nak.ttmg.model.*;
import de.nak.ttmg.util.ValidationException;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This is the interface for the Course Service
 */
public interface CourseService {

    Long createCourse(Course course, boolean force) throws ValidationException;

    void updateCourse(Long id, Course course, boolean force) throws ValidationException;

    List<Course> listCourses() throws ValidationException;

    Course loadCourse(Long id) throws ValidationException;

    void deleteCourse(Long id) throws ValidationException;
}
