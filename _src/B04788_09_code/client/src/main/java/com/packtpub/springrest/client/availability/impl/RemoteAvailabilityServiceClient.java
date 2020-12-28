package com.packtpub.springrest.client.availability.impl;

import com.packtpub.springrest.client.availability.AvailabilityServiceClient;
import com.packtpub.springrest.client.availability.AvailabilityStatus;
import com.packtpub.springrest.client.internal.ApiResponse;
import com.packtpub.springrest.client.internal.ResponseHandler;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * {@link AvailabilityServiceClient} implementation for remotely accessing the availability service.
 *
 * @author Ludovic Dewailly
 */
public class RemoteAvailabilityServiceClient implements AvailabilityServiceClient {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    private final String serviceUrl;
    private final RestTemplate template;

    public RemoteAvailabilityServiceClient(String serviceUrl) {
        if (serviceUrl == null) {
            throw new IllegalArgumentException("serviceUrl cannot be null");
        }
        this.serviceUrl = serviceUrl;
        template = new RestTemplate();
    }

    @Override public List<AvailabilityStatus> getAvailability(Date from, Date until, Long roomCategoryId) {
        if (from == null) {
            throw new IllegalArgumentException("from is mandatory");
        }
        if (until == null) {
            throw new IllegalArgumentException("until is mandatory");
        }
        if (from.getTime() > until.getTime()) {
            throw new IllegalArgumentException("from date must be before until date");
        }
        final StringBuilder url = new StringBuilder();
        url.append(serviceUrl).append("/availability?from=").append(DATE_FORMAT.format(from));
        url.append("&until=").append(DATE_FORMAT.format(until));
        if (roomCategoryId != null) {
            url.append("&roomCategoryId=").append(roomCategoryId);
        }
        return (List<AvailabilityStatus>) ResponseHandler.handle(
                () -> template.getForObject(url.toString(), ApiResponse.class));
    }
}
