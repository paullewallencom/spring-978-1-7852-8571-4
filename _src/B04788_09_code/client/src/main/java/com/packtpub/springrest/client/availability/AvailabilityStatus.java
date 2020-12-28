package com.packtpub.springrest.client.availability;

import com.packtpub.springrest.client.Room;

import java.util.List;

public class AvailabilityStatus {

    private final String date;
    private final List<Room> rooms;

    public AvailabilityStatus(String date, List<Room> rooms) {
        this.date = date;
        this.rooms = rooms;
    }

    public String getDate() {
        return date;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}