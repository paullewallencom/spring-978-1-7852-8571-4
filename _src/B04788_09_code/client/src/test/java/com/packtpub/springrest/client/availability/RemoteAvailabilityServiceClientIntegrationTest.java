package com.packtpub.springrest.client.availability;

import com.packtpub.springrest.client.availability.impl.RemoteAvailabilityServiceClient;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RemoteAvailabilityServiceClientIntegrationTest {

    @Test
    public void testGetAvailability() throws Exception {
        AvailabilityServiceClient client = new RemoteAvailabilityServiceClient("http://localhost:8080");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.JANUARY, 1, 0, 0);
        Date from = calendar.getTime();
        calendar.set(2017, Calendar.JANUARY, 2, 0, 0);
        Date until = calendar.getTime();
        List<AvailabilityStatus> statuses = client.getAvailability(from, until, null);
        assertNotNull(statuses);
    }
}