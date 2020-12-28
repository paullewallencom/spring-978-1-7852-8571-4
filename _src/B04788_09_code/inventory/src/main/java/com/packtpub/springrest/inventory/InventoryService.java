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

    public void updateRoomCategory(RoomCategory category);

    public RoomCategory getRoomCategory(long categoryId);

    /**
     * Deletes the room category with the given identifier. Note that this operation is likely to fail as it would leave
     * orphan data if rooms have been linked to this category
     *
     * <p>In a real system a clean up routine would need to be invoked.</p>
     *
     * @param categoryId the room category identifier
     *
     * @throws com.packtpub.springrest.RecordNotFoundException if the category does not exist
     */
    public void deleteRoomCategory(long categoryId);

    public List<RoomCategory> getAllRoomCategories();

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

    public void updateRoom(Room room);

    /**
     * Deletes the room with the given identifier. Note that this operation is likely to fail as it would leave orphan
     * data if bookings have already been made against the room.
     *
     * <p>In a real system a clean up routine would need to be invoked. Or the room should be marked as deleted but the
     * record not actually deleted.</p>
     *
     * @param roomId the room identifier
     *
     * @throws com.packtpub.springrest.RecordNotFoundException if the room does not exist
     */
    public void deleteRoom(long roomId);

    /**
     * Answers all the rooms in the system.
     *
     * @return all the {@link Room}s
     */
    public List<Room> getAllRooms();

    public List<Room> getAllRoomsWithCategory(RoomCategory category);
}
