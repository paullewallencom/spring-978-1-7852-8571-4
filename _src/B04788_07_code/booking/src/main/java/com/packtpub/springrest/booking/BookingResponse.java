package com.packtpub.springrest.booking;

/**
 * This class captures the result of processing a booking request.
 *
 * @author Ludovic Dewailly
 */
public class BookingResponse {

    public static enum Status {
        BOOKED,
        REJECTED
    }

    private final Status status;
    private long bookingId;

    public BookingResponse(Status status, long bookingId) {
        this.status = status;
        this.bookingId = bookingId;
    }

    public Status getStatus() {
        return status;
    }

    public long getBookingId() {
        return bookingId;
    }
}
