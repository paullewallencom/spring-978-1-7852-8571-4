package com.packtpub.springrest.client.availability;

import java.util.Date;
import java.util.List;

/**
 * Client for the availability service.
 *
 * @author Ludovic Dewailly
 */
public interface AvailabilityServiceClient {

    public List<AvailabilityStatus> getAvailability(Date from, Date until, Long roomCategoryId);
}
