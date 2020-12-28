package com.packtpub.springrest.init;

import com.packtpub.springrest.RecordNotFoundException;
import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Pricing;
import com.packtpub.springrest.model.PricingModel;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;

/**
 * Utility class for initialising the inventory service.
 *
 * @author Ludovic Dewailly
 */
public class InventoryServiceInitialiser {

    public static void init(InventoryService inventoryService) {
        try {
            inventoryService.getRoom(1);
        } catch (RecordNotFoundException e) {
            // generate default data
            RoomCategory category = new RoomCategory();
            category.setName("Double Rooms");
            category.setDescription("Rooms with double beds");
            Pricing pricing = new Pricing(PricingModel.FIXED);
            pricing.setPriceGuest1(10.0);
            category.setPricing(pricing);
            inventoryService.addRoomCategory(category);

            Room room = new Room();
            room.setName("Room 1");
            room.setDescription("Nice, spacious double bed room with usual amenities");
            room.setRoomCategory(category);
            inventoryService.addRoom(room);
        }
    }
}
