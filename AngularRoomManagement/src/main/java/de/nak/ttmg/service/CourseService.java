package de.nak.ttmg.service;

import de.nak.ttmg.model.*;
import de.nak.ttmg.exceptions.ValidationException;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * This is the interface for the Course Service
 */
public interface CourseService {

    Course createCourse(Course course, Boolean force) throws ValidationException;

    Course updateCourse(Long id, Course course, Boolean force) throws ValidationException;

    List<Course> listCourses() throws ValidationException;

    Course loadCourse(Long id) throws ValidationException;

    void deleteCourse(Long id) throws ValidationException;
}
