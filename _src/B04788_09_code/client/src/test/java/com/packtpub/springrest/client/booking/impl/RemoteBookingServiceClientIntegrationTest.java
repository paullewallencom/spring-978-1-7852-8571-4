package com.packtpub.springrest.client.booking.impl;

import com.packtpub.springrest.client.Booking;
import com.packtpub.springrest.client.booking.BookingServiceClient;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoteBookingServiceClientIntegrationTest {

    @Test
    public void testGetBooking() throws Exception {
        BookingServiceClient bookingServiceClient = new RemoteBookingServiceClient("http://localhost:8080", "admin", "admin");
        Booking booking = bookingServiceClient.getBooking(1);
        assertNotNull(booking);
    }
}