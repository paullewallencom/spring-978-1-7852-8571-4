package com.packtpub.springrest.inventory;

import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;

import java.util.List;

/**
 * This service provides the necessary functionality to manage rooms.
 *
 * @author Ludovic Dewailly
 */
public interface InventoryService {

    /**
     * Adds a room to the property.
     *
     * @param category the category the room belongs to
     */
    public void addRoomCategory(RoomCategory category);

    /**
     * Answers the room category with the given category identifier.
     *
     * @param categoryId the category ID
     *
     * @return the category
     */
    public RoomCategory getRoomCategory(long categoryId);

    /**
     * Adds a room to the system.
     *
     * @param room the room to add
     */
    public void addRoom(Room room);

    /**
     * Answers the room with the given identifier.
     *
     * @param roomId the room ID
     *
     * @return the room with the given {@code roomId}
     *
     * @throws com.packtpub.springrest.RecordNotFoundException if the room does not exist
     */
    public Room getRoom(long roomId);

    /**
     * Answers all rooms in the given category.
     *
     * @param category the room category
     *
     * @return all the rooms belonging with the given category
     */
    public List<Room> getAllRoomsWithCategory(RoomCategory category);
}
