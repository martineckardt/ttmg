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

    public List<Event> listEvents(Long centuriaId, Long tutorId, Long roomId, Long courseId, Date rangeStart, Date rangeEnd) throws ValidationException {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Event.class, "event");
        criteria.createAlias("event.course", "course"); // inner join by default

        if (centuriaId != null) {
            criteria.createAlias("course.participants", "centuria");
            criteria.add(Restrictions.eq("centuria.id", centuriaId));
        }
        if (tutorId != null) {
            criteria.createAlias("course.tutor", "tutor");
            criteria.add(Restrictions.eq("tutor.id", tutorId));
        }
        if (roomId != null) {
            criteria.createAlias("event.rooms", "room");
            criteria.add(Restrictions.eq("room.id", roomId));
        }
        if (courseId != null) {
            criteria.add(Restrictions.eq("course.id", courseId));
        }
        if (rangeStart != null) {
            criteria.add(Restrictions.ge("begin", rangeStart));
        }
        if (rangeEnd != null) {
            criteria.add(Restrictions.lt("begin", rangeEnd));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
