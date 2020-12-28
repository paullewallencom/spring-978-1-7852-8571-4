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

    /**
     * Looks up the booking with the given identifier.
     *
     * @param bookingId the booking identifier to look up
     *
     * @return the booking with the given ID
     */
    public Booking getBooking(long bookingId);

    /**
     * Answers all bookings for the given date range.
     *
     * @param dateRange the date range to retrieve bookings for
     *
     * @return the bookings in the given date range
     */
    public List<Booking> getBookings(DateRange dateRange);

    public List<Booking> getBookings(DateRange dateRange, RoomCategory roomCategory);

    /**
     * Processes the given booking
     *
     * @param request the booking request
     *
     * @return the result of the request
     */
    public BookingResponse book(BookingRequest request);

    public Booking getLastUpdatedBooking(DateRange dateRange);

    public Booking getLastUpdatedBooking(DateRange dateRange, RoomCategory roomCategory);
}
