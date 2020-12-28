package com.packtpub.springrest.booking.client;

import com.packtpub.springrest.booking.BookingService;
import com.packtpub.springrest.client.Booking;
import com.packtpub.springrest.client.booking.BookingServiceClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A local implementation of the booking service client.
 *
 * @author Ludovic Dewailly
 */
public class LocalBookingServiceClient implements BookingServiceClient {

    @Autowired
    private BookingService bookingService;

    @Override
    public Booking getBooking(long bookingId) {
        com.packtpub.springrest.model.Booking booking = bookingService.getBooking(bookingId);
        Booking clientBooking = new Booking();
        clientBooking.setId(booking.getId());
        clientBooking.setFrom(booking.getFrom());
        clientBooking.setUntil(booking.getUntil());
        clientBooking.setCategoryId(booking.getCategoryId());
        clientBooking.setCustomerName(booking.getCustomerName());
        return clientBooking;
    }
}
