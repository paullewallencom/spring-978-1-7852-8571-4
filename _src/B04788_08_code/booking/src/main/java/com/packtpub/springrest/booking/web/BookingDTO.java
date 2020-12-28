package com.packtpub.springrest.booking.web;

import com.packtpub.springrest.model.Booking;

public class BookingDTO {

    private long bookingId;
    private long roomId;
    private String customerName;

    public BookingDTO(Booking booking) {
        this.bookingId = booking.getId();
        this.roomId = booking.getRoomId();
        this.customerName = booking.getCustomerName();
    }

    public long getBookingId() {
        return bookingId;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getCustomerName() {
        return customerName;
    }
}
