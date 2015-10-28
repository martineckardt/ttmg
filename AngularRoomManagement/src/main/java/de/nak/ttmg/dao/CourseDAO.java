package de.nak.ttmg.dao;

import de.nak.ttmg.model.Course;
import de.nak.ttmg.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class CourseDAO {
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Course> findAll() {
        return entityManager.createQuery("select event from Course course").getResultList();
    }

    public Course load(Long id) {
        return entityManager.find(Course.class, id);
    }

    public Long create(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
            return course.getId();
        }
        return null;
    }

    public void update(Course course) {
        entityManager.merge(course);
    }

    public void delete(Course course) {
        entityManager.refresh(course);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
