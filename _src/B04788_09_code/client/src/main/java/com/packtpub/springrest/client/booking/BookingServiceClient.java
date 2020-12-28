package com.packtpub.springrest.client.booking;

import com.packtpub.springrest.client.Booking;

/**
 * Client interface for the booking service.
 *
 * @author Ludovic Dewailly
 */
public interface BookingServiceClient {

    /**
     * Looks up the booking with the given identifier.
     *
     * @param bookingId the booking identifier to look up
     *
     * @return the booking with the given ID
     */
    public Booking getBooking(long bookingId);
}
