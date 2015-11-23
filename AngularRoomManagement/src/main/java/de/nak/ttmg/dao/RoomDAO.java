package de.nak.ttmg.dao;

import de.nak.ttmg.exceptions.EntityNotFoundException;
import de.nak.ttmg.model.Room;
import de.nak.ttmg.model.RoomType;
import de.nak.ttmg.exceptions.ValidationException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by sebastian on 28/10/15.
 * The Data Access Object for Rooms
 */
public class RoomDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Room> findAll(String building, String roomNbr, RoomType type, Integer minSeats) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Room.class);

        //Filter results to match all criteria
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
        //We only want each room once
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public Room load(Long id) {
        Room room = entityManager.find(Room.class, id);
        if (room == null) {
            throw new EntityNotFoundException("room", id);
        }
        return room;
    }

    public Room create(Room room) throws ValidationException {
        entityManager.persist(room);
        return room;
    }

    public void delete(Room room) throws ValidationException {
        entityManager.remove(room);
    }
}
