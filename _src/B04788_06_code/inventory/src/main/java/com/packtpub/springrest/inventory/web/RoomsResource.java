package com.packtpub.springrest.inventory.web;

import com.packtpub.springrest.RecordNotFoundException;
import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;
import com.packtpub.springrest.web.ApiResponse;
import com.packtpub.springrest.web.ApiResponse.ApiError;
import com.packtpub.springrest.web.ApiResponse.Status;
import com.packtpub.springrest.web.ListApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Resource (controller) class for {@link Room}s.
 *
 * @author Ludovic Dewailly
 */
@RestController
@RequestMapping("/rooms")
public class RoomsResource {

    @Autowired
    private InventoryService inventoryService;

    public RoomsResource() {}

    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public ApiResponse getRoom(@PathVariable("roomId") long id) {
        try {
            Room room = inventoryService.getRoom(id);
            return new ApiResponse(Status.OK, new RoomDTO(room), null);
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERROR, null, new ApiError(999, "No room with ID " + id));
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse addRoom(@RequestBody RoomDTO room) {
        Room newRoom = createRoom(room);
        return new ApiResponse(Status.OK, new RoomDTO(newRoom), null);
    }

    private Room createRoom(RoomDTO room) {
        return createRoom(room.getName(), room.getDescription(), room.getRoomCategoryId());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ApiResponse addRoom(String name, String description, long roomCategoryId) {
        Room room = createRoom(name, description, roomCategoryId);
        return new ApiResponse(Status.OK, new RoomDTO(room));

    }

    private Room createRoom(String name, String description, long roomCategoryId) {
        Room room = new Room();
        room.setName(name);
        room.setDescription(description);
        RoomCategory category = inventoryService.getRoomCategory(roomCategoryId);
        room.setRoomCategory(category);
        inventoryService.addRoom(room);
        return room;
    }

    @RequestMapping(value = "/{roomId}", method = RequestMethod.PUT)
    public ApiResponse updateRoom(@PathVariable("roomId") long id, @RequestBody RoomDTO updatedRoom) {
        try {
            Room room = inventoryService.getRoom(id);
            updateRoom(updatedRoom, room);
            return new ApiResponse(Status.OK, new RoomDTO(room));
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERROR, null, new ApiError(999, "No room with ID " + id));
        }
    }

    @RequestMapping(value = "/{roomId}", method = RequestMethod.POST, headers = {"X-HTTP-Method-Override=PUT"})
    public ApiResponse updateRoomAsPost(@PathVariable("roomId") long id, @RequestBody RoomDTO updatedRoom) {
        return updateRoom(id, updatedRoom);
    }

    private void updateRoom(RoomDTO updatedRoom, Room room) {
        room.setName(updatedRoom.getName());
        room.setDescription(updatedRoom.getDescription());
        room.setRoomCategory(inventoryService.getRoomCategory(updatedRoom.getRoomCategoryId()));
        inventoryService.updateRoom(room);
    }

    @RequestMapping(value = "/{roomId}", method = RequestMethod.DELETE)
    public ApiResponse deleteRoom(@PathVariable("roomId") long id) {
        try {
            Room room = inventoryService.getRoom(id);
            inventoryService.deleteRoom(room.getId());
            return new ApiResponse(Status.OK, null);
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERROR, null, new ApiError(999, "No room with ID " + id));
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ListApiResponse getRoomsInCategory(@RequestParam("categoryId") long categoryId) {
        RoomCategory category = inventoryService.getRoomCategory(categoryId);
        return new ListApiResponse(Status.OK,inventoryService.getAllRoomsWithCategory(category)
                .stream().map(RoomDTO::new).collect(Collectors.toList()), null, 2,
                "http://localhost:8080/rooms?categoryId=" + categoryId + "&page=3", 13);
    }
}
