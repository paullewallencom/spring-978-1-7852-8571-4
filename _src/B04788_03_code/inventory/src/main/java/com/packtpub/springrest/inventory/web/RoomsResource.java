package com.packtpub.springrest.inventory.web;

import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public RoomDTO getRoom(@PathVariable("roomId") long id) {
        Room room = inventoryService.getRoom(id);
        return new RoomDTO(room);
    }

    @RequestMapping(    method = RequestMethod.GET)
    public List<RoomDTO> getRoomsInCategory(@RequestParam("categoryId") long categoryId) {
        RoomCategory category = inventoryService.getRoomCategory(categoryId);
        return inventoryService.getAllRoomsWithCategory(category)
                .stream().map(RoomDTO::new).collect(Collectors.toList());
    }
}
