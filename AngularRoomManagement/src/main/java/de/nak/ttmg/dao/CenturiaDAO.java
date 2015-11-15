package de.nak.ttmg.dao;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.StudyProgram;
import de.nak.ttmg.util.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class CenturiaDAO {
    @PersistenceContext
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
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public Centuria load(Long id) {
        return entityManager.find(Centuria.class, id);
    }

    public Centuria create(Centuria centuria) throws ValidationException{
        entityManager.persist(centuria);
        return centuria;
    }

    public void delete(Centuria centuria) throws ValidationException {
        entityManager.remove(centuria);
    }
}
