package de.nak.ttmg.validator;

import de.nak.ttmg.exceptions.ValidationException;
import de.nak.ttmg.model.Course;

/**
 * Created by Martin Eckardt on 27.11.2015.
 */
public interface CourseValidator {
    void validateCourse(Course course, boolean force) throws ValidationException;
}
