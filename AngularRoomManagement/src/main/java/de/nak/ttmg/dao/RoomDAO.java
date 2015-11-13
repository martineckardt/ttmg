package de.nak.ttmg.dao;

import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
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
public class RoomDAO {

    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Room> findAll(String building, String roomNbr, RoomType type, Integer minSeats) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Room.class);
        if (building != null) {
            criteria.add(Restrictions.eq("building", building));
        }
        if (roomNbr != null) {
            criteria.add(Restrictions.eq("room_number", roomNbr));
        }
        if (type != null) {
            criteria.add(Restrictions.eq("type", type));
        }
        if (minSeats != null) {
            criteria.add(Restrictions.ge("seats", minSeats));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public Room load(Long id) {
        return entityManager.find(Room.class, id);
    }

    public Room create(Room room) throws ValidationException {
        entityManager.persist(room);
        return room;
    }

    public void delete(Room room) throws ValidationException {
        entityManager.remove(room);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
