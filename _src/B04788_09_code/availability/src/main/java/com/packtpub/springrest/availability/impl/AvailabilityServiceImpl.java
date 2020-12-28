package com.packtpub.springrest.availability.impl;

import com.packtpub.springrest.availability.AvailabilityQuery;
import com.packtpub.springrest.availability.AvailabilityService;
import com.packtpub.springrest.availability.AvailabilityStatus;
import com.packtpub.springrest.booking.BookingService;
import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Booking;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {@link AvailabilityService} implementation.
 *
 * @author Ludovic Dewailly
 */
@Component
public class AvailabilityServiceImpl implements AvailabilityService {

    private static final int ONE_DAY = 24*60*60*1000;

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private BookingService bookingService;

    @Override
    public Date getLastUpdatedBooking(AvailabilityQuery query) {
        if (query == null) {
            throw new IllegalArgumentException("query cannot be null");
        }
        Booking booking;
        if (query.getCategoryId() <= 0) {
            booking = bookingService.getLastUpdatedBooking(query.getDateRange());
        } else {
            RoomCategory category = inventoryService.getRoomCategory(query.getCategoryId());
            booking = bookingService.getLastUpdatedBooking(query.getDateRange(), category);
        }
        return booking == null ? null : booking.getUpdatedAt();
    }

    @Override
    public List<AvailabilityStatus> getAvailableRooms(AvailabilityQuery query) {
        if (query == null) {
            throw new IllegalArgumentException("query cannot be null");
        }
        // we look up bookings and rooms
        List<Booking> bookings;
        List<Room> allRooms;
        if (query.getCategoryId() <= 0) {
            bookings = bookingService.getBookings(query.getDateRange());
            allRooms = inventoryService.getAllRooms();
        } else {
            RoomCategory category = inventoryService.getRoomCategory(query.getCategoryId());
            allRooms = inventoryService.getAllRoomsWithCategory(category);
            bookings = bookingService.getBookings(query.getDateRange(), category);
        }
        // and split statuses by day
        List<AvailabilityStatus> statuses = new ArrayList<>();
        Date date = query.getDateRange().getFrom();
        while (date.getTime() <= query.getDateRange().getUntil().getTime()) {
            statuses.add(new AvailabilityStatus(date, toRoomList(allRooms, getBookedRoomsOn(bookings, date))));
            date = new Date(date.getTime() + ONE_DAY);
        }
        return statuses;
    }

    private Set<Long> getBookedRoomsOn(List<Booking> bookings, Date date) {
        Set<Long> roomIds = new HashSet<>();
        long time = date.getTime();
        roomIds.addAll(bookings.stream()
                .filter(booking -> booking.getFrom().getTime() >= time && booking.getUntil().getTime() <= time)
                .map(Booking::getRoomId)
                .collect(Collectors.toList()));
        return roomIds;
    }

    private List<Room> toRoomList(List<Room> allRooms, Set<Long> bookedRoomIds) {
        return allRooms
                .stream()
                .filter(room -> !bookedRoomIds.contains(room.getId()))
                .collect(Collectors.toList());
    }
}
