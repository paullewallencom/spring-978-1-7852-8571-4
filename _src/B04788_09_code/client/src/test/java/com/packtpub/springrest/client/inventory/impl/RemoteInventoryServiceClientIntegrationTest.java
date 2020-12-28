package com.packtpub.springrest.client.inventory.impl;

import com.packtpub.springrest.client.Room;
import com.packtpub.springrest.client.inventory.InventoryServiceClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RemoteInventoryServiceClientIntegrationTest {

    @Test
    public void testGetRoom() throws Exception {
        InventoryServiceClient client = new RemoteInventoryServiceClient("http://localhost:8080");
        Room room = client.getRoom(1);
        assertNotNull(room);
        assertEquals(1, room.getId());
    }
}