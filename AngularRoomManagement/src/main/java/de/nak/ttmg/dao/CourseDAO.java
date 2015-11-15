package de.nak.ttmg.dao;

import de.nak.ttmg.model.Course;
import de.nak.ttmg.util.EntityAlreadyExistsException;
import de.nak.ttmg.util.EntityNotFoundException;
import de.nak.ttmg.util.InvalidParameterException;
import de.nak.ttmg.util.ValidationException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * The Data Access Object for Courses
 */
public class CourseDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Course> findAll() {
        return entityManager.createQuery("select course from Course course").getResultList();
    }

    public Course load(Long id) throws ValidationException {
        Course course = entityManager.find(Course.class, id);
        if (course != null) {
            return course;
        }
        throw new EntityNotFoundException("course", id);
    }

    public Course create(Course course) throws ValidationException {
        if (course.getId() == null) {
            try {
                entityManager.persist(course);
            } catch (ConstraintViolationException e) {
                throw new EntityAlreadyExistsException();
            } catch (Exception e) {
                throw new ValidationException(e);
            }
            return course;
        } else {
            throw new InvalidParameterException("id", InvalidParameterException.InvalidParameterType.INVALID_NOT_NULL);
        }
    }

    public Course update(Course course) {
        entityManager.merge(course);
        return course;
    }

    public void delete(Course course) {
        entityManager.remove(course);
    }
}
