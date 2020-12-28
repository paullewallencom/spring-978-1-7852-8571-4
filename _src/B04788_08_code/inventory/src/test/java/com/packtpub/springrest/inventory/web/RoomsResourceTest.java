package com.packtpub.springrest.inventory.web;

import com.packtpub.springrest.RecordNotFoundException;
import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;
import com.packtpub.springrest.web.ApiResponse;
import com.packtpub.springrest.web.ApiResponse.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomsResourceTest {

    @InjectMocks
    private RoomsResource resource;

    @Mock
    private InventoryService inventoryService;

    @Test
    public void testGetRoom() throws Exception {
        InventoryService inventoryService = new InventoryService() {
            @Override
            public void addRoomCategory(RoomCategory category) {

            }

            @Override
            public void updateRoomCategory(RoomCategory category) {

            }

            @Override
            public RoomCategory getRoomCategory(long categoryId) {
                return null;
            }

            @Override
            public void deleteRoomCategory(long categoryId) {

            }

            @Override
            public List<RoomCategory> getAllRoomCategories() {
                return null;
            }

            @Override
            public void addRoom(Room room) {

            }

            @Override
            public Room getRoom(long roomId) {
                if (roomId == 1) {
                    Room room = new Room();
                    room.setName("Room1");
                    room.setDescription("Room description");
                    RoomCategory category = new RoomCategory();
                    category.setName("Category1");
                    category.setDescription("Category description");
                    room.setRoomCategory(category);
                    return room;
                } else {
                    throw new RecordNotFoundException("");
                }
            }

            @Override
            public void updateRoom(Room room) {

            }

            @Override
            public void deleteRoom(long roomId) {

            }

            @Override
            public List<Room> getAllRooms() {
                return null;
            }

            @Override
            public List<Room> getAllRoomsWithCategory(RoomCategory category) {
                return null;
            }
        };
        RoomsResource resource = new RoomsResource(inventoryService);
        ApiResponse response = resource.getRoom(1);
        assertEquals(Status.OK, response.getStatus());
        assertNotNull(response);
        RoomDTO room = (RoomDTO) response.getData();
        assertNotNull(room);
        assertEquals("Room1", room.getName());
        // non-existent room
        response = resource.getRoom(2);
        assertEquals(Status.ERROR, response.getStatus());
        assertEquals(999, response.getError().getErrorCode());

    }

    @Test
    public void testUpdateRoom() {
        RoomCategory category = new RoomCategory();
        Room room = new Room();
        room.setRoomCategory(category);
        room.setName("The Room");
        room.setDescription("It is The room");
        when(inventoryService.getRoom(1)).thenReturn(room);
        when(inventoryService.getRoom(2)).thenThrow(new RecordNotFoundException(""));
        when(inventoryService.getRoomCategory(anyLong())).thenReturn(category);
        Room updatedRoom = new Room();
        updatedRoom.setName(room.getName());
        updatedRoom.setDescription(room.getDescription() + ". It's an awesome room!");
        updatedRoom.setRoomCategory(category);
        RoomDTO dto = new RoomDTO(updatedRoom);
        ApiResponse response = resource.updateRoom(1, dto);
        assertEquals(Status.OK, response.getStatus());
        assertEquals("It is The room. It's an awesome room!", ((RoomDTO) response.getData()).getDescription());
    }
}