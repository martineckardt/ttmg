package de.nak.ttmg.dao;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.model.Tutor;
import de.nak.ttmg.util.EntityAlreadyExistsException;
import de.nak.ttmg.util.InvalidParameterException;
import de.nak.ttmg.util.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class CenturiaDAO {
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Centuria> findAll(Integer year, StudyProgram program) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Centuria.class);
        if (year != null) {
            criteria.add(Restrictions.eq("year", year));
        }
        if (program != null) {
            criteria.add(Restrictions.eq("program", program));
        }
        return criteria.list();
    }

    public Centuria load(Long id) {
        return entityManager.find(Centuria.class, id);
    }

    public Long create(Centuria centuria) throws ValidationException{
        entityManager.persist(centuria);
        return centuria.getId();
    }

    public void delete(Centuria centuria) throws ValidationException {
        entityManager.detach(centuria);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
