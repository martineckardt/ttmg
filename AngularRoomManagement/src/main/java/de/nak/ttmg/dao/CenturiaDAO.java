package de.nak.ttmg.dao;

import de.nak.ttmg.model.Centuria;
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
public class CenturiaDAO {
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Centuria> findAll() {
        return entityManager.createQuery("select centuria from Centuria centuria").getResultList();

    }

    public Centuria load(Long id) {
        return entityManager.find(Centuria.class, id);
    }

    public Long create(Centuria centuria) throws ValidationException{
        if (centuria.getId() == null) {
            try {
                entityManager.persist(centuria);
                return centuria.getId();
            } catch (ConstraintViolationException e) {
                throw new EntityAlreadyExistsException();
            } catch (Exception e) {
                throw new ValidationException(e);
            }
        } else {
            throw new InvalidParameterException("centuriaId", InvalidParameterException.InvalidParameterType.INVALID_NOT_NULL);
        }
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
