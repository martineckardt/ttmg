package de.nak.ttmg.dao;

import de.nak.ttmg.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by felixb on 28/10/15.
 */
public class EventDAO {
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Event> findAll() {
        return entityManager.createQuery("select event from Event event").getResultList();
    }

    public Event load(Long id) {
        return entityManager.find(Event.class, id);
    }

    public void create(Event event) {
        if (event.getId() == null) {
            entityManager.persist(event);
        } else {
            entityManager.merge(event);
        }
    }

    public void delete(Event event) {
        entityManager.refresh(event);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
