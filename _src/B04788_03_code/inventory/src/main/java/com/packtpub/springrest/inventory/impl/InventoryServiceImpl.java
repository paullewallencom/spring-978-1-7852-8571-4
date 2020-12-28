package com.packtpub.springrest.inventory.impl;

import com.packtpub.springrest.RecordNotFoundException;
import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Pricing;
import com.packtpub.springrest.model.PricingModel;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link InventoryService} implementation.
 *
 * @author Ludovic Dewailly
 */
@Component
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRoomCategory(RoomCategory category) {
        if (category.getId() > 0) {
            throw new IllegalArgumentException("category already exists");
        }
        checkPricing(category);
        sessionFactory.getCurrentSession().save(category.getPricing());
        sessionFactory.getCurrentSession().save(category);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Added new room category {}", category);
        }
    }

    /**
     * Validates that the pricing object is valid.
     *
     * @param category the category to check the pricing for
     */
    private void checkPricing(RoomCategory category) {
        Pricing pricing = category.getPricing();
        if (pricing == null) {
            throw new IllegalArgumentException("category must have a pricing");
        }
        if (pricing.getPriceGuest1() == null) {
            throw new IllegalArgumentException("Pricing requires a guest 1 price");
        }
        if (pricing.getPricingModel() == PricingModel.SLIDING && pricing.getPriceGuest2() == null) {
            throw new IllegalArgumentException("Sliding pricing requires a guest 2 price");
        }
    }

    @Override
    public RoomCategory getRoomCategory(long categoryId) {
        if (categoryId <= 0) {
            throw new IllegalArgumentException("Invalid category ID. It must be greater than zero");
        }
        RoomCategory category = (RoomCategory) sessionFactory.getCurrentSession().get(RoomCategory.class, categoryId);
        if (category == null) {
            throw new RecordNotFoundException("No room category with ID " + categoryId);
        }
        return category;
    }

    @Override
    public void addRoom(Room room) {
        if (room.getId() > 0) {
            throw new IllegalArgumentException("room already exists");
        }
        sessionFactory.getCurrentSession().save(room);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Added new room {}", room);
        }
    }

    @Transactional(readOnly = true)
    public Room getRoom(long roomId) {
        if (roomId <= 0) {
            throw new IllegalArgumentException("Invalid room ID. It must be greater than zero");
        }
        Room room = (Room) sessionFactory.getCurrentSession().get(Room.class, roomId);
        if (room == null) {
            throw new RecordNotFoundException("No room with ID " + roomId);
        }
        return room;
    }
    @Transactional(readOnly = true)
    public List<Room> getAllRoomsWithCategory(RoomCategory category) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Room.class)
                .add(Restrictions.eq("roomCategory.id", category.getId()))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }
}
