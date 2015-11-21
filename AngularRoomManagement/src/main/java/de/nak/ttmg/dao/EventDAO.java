package de.nak.ttmg.dao;

import de.nak.ttmg.model.DateRange;
import de.nak.ttmg.model.Event;
import de.nak.ttmg.exceptions.EntityAlreadyExistsException;
import de.nak.ttmg.exceptions.EntityNotFoundException;
import de.nak.ttmg.exceptions.InvalidParameterException;
import de.nak.ttmg.exceptions.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felixb on 04/11/15.
 * The Data Access Object for Events
 */
public class EventDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Event> listEvents(Long centuriaId, Long tutorId, Long roomId, Long courseId, DateRange range) throws ValidationException {
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
        if (range != null) {
            criteria.add(Restrictions.ge("begin", range.getBegin()));
            //check if the event STARTS before the rangeEndDate
            criteria.add(Restrictions.lt("begin", range.getEnd()));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public Event load(Long id) throws ValidationException {
        Event event = entityManager.find(Event.class, id);
        if (event != null) {
            return event;
        }
        throw new EntityNotFoundException("event", id);
    }

    public Event create(Event event) throws ValidationException {
        if (event.getId() == null) {
            try {
                entityManager.persist(event);
            } catch (ConstraintViolationException e) {
                throw new EntityAlreadyExistsException();
            } catch (Exception e) {
                throw new ValidationException(e);
            }
            return event;
        } else {
            throw new InvalidParameterException("id", InvalidParameterException.InvalidParameterType.INVALID_NOT_NULL);
        }
    }

    public List<Event> createEvents(List<Event> events) throws ValidationException {
        Session session = entityManager.unwrap(Session.class);
        Transaction t = session.beginTransaction();
        try {
            List<Event> newEvents = new ArrayList<>(events.size());
            for (Event event : events) {
                newEvents.add(create(event));
            }
            t.commit();
            return newEvents;
        } catch (ValidationException e) {
            t.rollback();
            throw e;
        }
    }

    public Event update(Event event) {
        entityManager.merge(event);
        return event;
    }

    public void delete(Event event) {
        entityManager.remove(event);
    }
}
