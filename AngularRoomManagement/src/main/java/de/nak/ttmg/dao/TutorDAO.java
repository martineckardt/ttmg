package de.nak.ttmg.dao;

import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.util.EntityAlreadyExistsException;
import de.nak.ttmg.util.InvalidParameterException;
import de.nak.ttmg.util.ValidationException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class TutorDAO {
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Tutor> findAll() {
        return entityManager.createQuery("select tutor from Tutor tutor").getResultList();
    }

    public Tutor load(Long id) {
        return entityManager.find(Tutor.class, id);
    }

    public Long create(Tutor tutor) throws ValidationException {
        if (tutor.getId() == null) {
            try {
                entityManager.persist(tutor);
                return tutor.getId();
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
        entityManager.detach(tutor);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
