package de.nak.ttmg.dao;

import de.nak.ttmg.model.Centuria;

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

    public void create(Centuria centuria) {
        if (centuria.getId() == null) {
            entityManager.persist(centuria);
        } else {
            // tutor already persistet
        }
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
