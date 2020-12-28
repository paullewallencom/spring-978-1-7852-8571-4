package com.packtpub.springrest.booking.web;

import com.packtpub.springrest.booking.BookingService;
import com.packtpub.springrest.model.Booking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:booking-test.xml")
public class BookingsResourceTest {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingsResource resource;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testGetBookingNotLoggedIn() throws Exception {
        resource.getBooking(1);
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser
    public void testGetBookingNotAdmin() throws Exception {
        resource.getBooking(1);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testGetBookingValidUser() throws Exception {
        when(bookingService.getBooking(1)).thenReturn(new Booking());
        assertNotNull(resource.getBooking(1));
    }
}