package com.packtpub.springrest.booking;

import com.packtpub.springrest.DateRange;
import com.packtpub.springrest.model.Booking;
import com.packtpub.springrest.model.RoomCategory;

import java.util.List;

/**
 * This service handles booking requests.
 *
 * @author Ludovic Dewailly
 */
public interface BookingService {

    public BookingResponse book(BookingRequest request);

    public Booking getBooking(long bookingId);

    public List<Booking> getBookings(DateRange dateRange);

    public List<Booking> getBookings(DateRange dateRange, RoomCategory roomCategory);

    public Booking getLastUpdatedBooking(DateRange dateRange);

    public Booking getLastUpdatedBooking(DateRange dateRange, RoomCategory roomCategory);
}
