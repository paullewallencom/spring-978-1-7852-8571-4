package com.packtpub.springrest.availability.web;

import com.packtpub.springrest.availability.AvailabilityService;
import com.packtpub.springrest.web.ApiResponse;
import com.packtpub.springrest.web.ApiResponse.Status;
import org.junit.Test;
import org.springframework.web.context.request.WebRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AvailabilityResourceTest {

    @Test
    public void testGetAvailability() throws Exception {
        AvailabilityService service = mock(AvailabilityService.class);

        AvailabilityResource resource = new AvailabilityResource(service);
        WebRequest request = null;
        // invalid from date
        ApiResponse response = resource.getAvailability(null, "2017-01-02", "1", request);
        assertEquals(Status.ERROR, response.getStatus());
        assertEquals(17, response.getError().getErrorCode());
        // from is after until
        response = resource.getAvailability("2017-01-03", "2017-01-02", "1", request);
        assertEquals(Status.ERROR, response.getStatus());
        assertEquals(17, response.getError().getErrorCode());
    }
}