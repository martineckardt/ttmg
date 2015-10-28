package de.nak.ttmg.dao;

import de.nak.ttmg.model.Tutor;

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

    public void create(Tutor tutor) {
        if (tutor.getId() == null) {
            entityManager.persist(tutor);
        } else {
            // tutor already persistet
        }
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
