package de.nak.ttmg.dao;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.exceptions.EntityAlreadyExistsException;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * The Data Access Object for Tutors
 */
public class TutorDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Tutor> findAll() {
        return entityManager.createQuery("select tutor from Tutor tutor").getResultList();
    }

    public Tutor load(Long id) {
        return entityManager.find(Tutor.class, id);
    }

    public Tutor create(Tutor tutor) throws ValidationException {
        if (tutor.getId() == null) {
            try {
                entityManager.persist(tutor);
                return tutor;
            } catch (ConstraintViolationException e) {
                throw new EntityAlreadyExistsException();
            } catch (Exception e) {
                throw new ValidationException(e);
            }
        } else {
            throw new InvalidParameterException("tutorId", InvalidParameterException.InvalidParameterType.INVALID_NOT_NULL);
        }
    }

    public void delete(Tutor tutor) throws ValidationException {
        entityManager.remove(tutor);
    }
}
