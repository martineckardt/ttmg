package de.nak.ttmg.dao;

import de.nak.ttmg.exceptions.EntityNotFoundException;
import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.exceptions.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 * The Data Access Object for Centurias
 */
public class CenturiaDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Centuria> findAll(Integer year, StudyProgram program) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Centuria.class);
        //Filter results to match all criteria
        if (year != null) {
            criteria.add(Restrictions.eq("year", year));
        }
        if (program != null) {
            criteria.add(Restrictions.eq("program", program));
        }
        //We only want each centuria once
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public Centuria load(Long id) {
        Centuria centuria = entityManager.find(Centuria.class, id);
        if (centuria != null) {
            return centuria;
        }
        throw new EntityNotFoundException("centuria", id);
    }

    public Centuria create(Centuria centuria) throws ValidationException{
        entityManager.persist(centuria);
        return centuria;
    }

    public void delete(Centuria centuria) throws ValidationException {
        entityManager.remove(centuria);
    }
}
