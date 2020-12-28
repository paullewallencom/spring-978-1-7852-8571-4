package com.packtpub.springrest.client.inventory.impl;

import com.packtpub.springrest.client.Room;
import com.packtpub.springrest.client.internal.ApiResponse;
import com.packtpub.springrest.client.internal.ResponseHandler;
import com.packtpub.springrest.client.inventory.InventoryServiceClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Remote implementation for {@link InventoryServiceClient}.
 *
 * @author Ludovic Dewailly
 */
public class RemoteInventoryServiceClient implements InventoryServiceClient {

    private final String serviceUrl;
    private final RestTemplate template;

    public RemoteInventoryServiceClient(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        template = new RestTemplate();
    }

    @Override
    public Room getRoom(long roomId) {
        if (roomId <= 0) {
            throw new IllegalArgumentException("roomId must be greater than zero");
        }
        ParameterizedTypeReference<ApiResponse<Room>> typeReference = new ParameterizedTypeReference<ApiResponse<Room>>() {};
        return (Room) ResponseHandler.handle(
                () -> template.exchange(serviceUrl + "/rooms/" + roomId, HttpMethod.GET, null, typeReference).getBody());
    }
}
