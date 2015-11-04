package de.nak.ttmg.dao;

import de.nak.ttmg.model.Centuria;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.util.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by felixb on 04/11/15.
 */
public class EventDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Event> listEvents(Long centuriaId, Long tutorId, Long roomId) throws ValidationException {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Event.class);
        //if (centuriaId != null) {
        //    criteria.add(Restrictions.eq("centuriaId", centuriaId));
        //}
        //if (tutorId != null) {
        //    criteria.add(Restrictions.eq("tutorId", tutorId));
        //}
        if (roomId != null) {
            criteria.add(Restrictions.eq("roomId", roomId));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }
}
