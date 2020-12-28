package com.packtpub.springrest.availability;

import java.util.Date;
import java.util.List;

/**
 * Our availability service is responsible for working out what rooms are available for a given period.
 *
 * @author Ludovic Dewailly
 */
public interface AvailabilityService {

    /**
     * Answers the date of the last updated booking for the given availability query.
     *
     * @param query the availability query
     *
     * @return the last update on bookings
     */
    public Date getLastUpdatedBooking(AvailabilityQuery query);

    /**
     * Answers the availability status for the given query.
     *
     * @param query the availability query
     *
     * @return the availability status for each day in the requested period.
     */
    public List<AvailabilityStatus> getAvailableRooms(AvailabilityQuery query);
}
