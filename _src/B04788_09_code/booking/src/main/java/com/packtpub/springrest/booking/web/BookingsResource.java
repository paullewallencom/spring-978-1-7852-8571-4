package com.packtpub.springrest.booking.web;

import com.packtpub.springrest.RecordNotFoundException;
import com.packtpub.springrest.booking.BookingRequest;
import com.packtpub.springrest.booking.BookingResponse;
import com.packtpub.springrest.booking.BookingService;
import com.packtpub.springrest.web.ApiResponse;
import com.packtpub.springrest.web.ApiResponse.ApiError;
import com.packtpub.springrest.web.ApiResponse.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse book(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.book(request);
        return new ApiResponse(Status.OK, response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{bookingId}", method = RequestMethod.GET)
    public ApiResponse getBooking(@PathVariable("bookingId") long bookingId) {
        try {
            return new ApiResponse(Status.OK, new BookingDTO(bookingService.getBooking(bookingId)));
        } catch (RecordNotFoundException e) {
            return new ApiResponse(Status.ERROR, null, new ApiError(999, "No booking with ID " + bookingId));
        }
    }
}
