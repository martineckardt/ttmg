package de.nak.ttmg.dao;

import de.nak.ttmg.model.Room;
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
public class RoomDAO {

    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Room> findAll() {
        return entityManager.createQuery("select room from Room room").getResultList();
    }

    public Room load(Long id) {
        return entityManager.find(Room.class, id);
    }

    public Long create(Room room) throws ValidationException {
        entityManager.persist(room);
        return room.getId();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
