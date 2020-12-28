package com.packtpub.springrest.client.inventory;

import com.packtpub.springrest.client.Room;

/**
 * Client for the inventory service.
 *
 * @author Ludovic
 */
public interface InventoryServiceClient {

    public Room getRoom(long roomId);
}
