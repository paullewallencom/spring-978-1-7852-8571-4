package com.packtpub.springrest.booking.init;

import com.packtpub.springrest.DateRange;
import com.packtpub.springrest.booking.BookingRequest;
import com.packtpub.springrest.booking.BookingService;
import com.packtpub.springrest.booking.CreditCardDetails;
import com.packtpub.springrest.model.Booking;

import java.util.List;

/**
 * Initialiser for the booking service.
 *
 * @author Ludovic Dewailly
 */
public class BookingServiceInitialiser {

    public static void init(BookingService bookingService) {
        List<Booking> bookings = bookingService.getBookings(new DateRange());
        if (bookings.isEmpty()) {
            CreditCardDetails creditCardDetails = new CreditCardDetails("Jane Doe", "1111-11111-1111-1111", "08/19", "111");
            bookingService.book(new BookingRequest(1, new DateRange(), "John Doe", creditCardDetails));
        }
    }
}
