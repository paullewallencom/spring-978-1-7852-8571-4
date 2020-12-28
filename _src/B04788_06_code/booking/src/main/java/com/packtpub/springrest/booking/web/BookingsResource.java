package com.packtpub.springrest.booking.web;

import com.packtpub.springrest.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resource (controller) class for {@link com.packtpub.springrest.model.Booking}s.
 *
 * @author Ludovic Dewailly
 */
@RestController
@RequestMapping("/bookings")
public class BookingsResource {

    @Autowired
    private BookingService bookingService;

    public BookingsResource() {}
}
