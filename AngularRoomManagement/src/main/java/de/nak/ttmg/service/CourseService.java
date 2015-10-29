package de.nak.ttmg.service;

import de.nak.ttmg.model.*;
import de.nak.ttmg.util.ValidationException;

import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public interface CourseService {

    Long createCourse(Course course, boolean force) throws ValidationException;

    Boolean updateCourse(Course course, boolean force) throws ValidationException;

    List<Course> listCourses() throws ValidationException;

    Course loadCourse(Long id) throws ValidationException;

    Boolean deleteCourse(Long id) throws ValidationException;
}
