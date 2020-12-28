package com.packtpub.springrest.availability.web;

import com.packtpub.springrest.inventory.web.RoomDTO;
import com.packtpub.springrest.model.Room;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.packtpub.springrest.availability.AvailabilityStatus}.
 *
 * @author Ludovic Dewailly
 */
public class AvailabilityStatusDTO {

    private final String date;
    private final List<RoomDTO> rooms;

    public AvailabilityStatusDTO(String date, List<Room> rooms) {
        this.date = date;
        this.rooms = rooms
                .stream()
                .map(RoomDTO::new)
                .collect(Collectors.toList());
    }

    public String getDate() {
        return date;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }
}
