package com.packtpub.springrest.booking.impl;

import com.packtpub.springrest.DateRange;
import com.packtpub.springrest.RecordNotFoundException;
import com.packtpub.springrest.booking.BookingRequest;
import com.packtpub.springrest.booking.BookingResponse;
import com.packtpub.springrest.booking.BookingResponse.Status;
import com.packtpub.springrest.booking.BookingService;
import com.packtpub.springrest.inventory.InventoryService;
import com.packtpub.springrest.model.Booking;
import com.packtpub.springrest.model.Room;
import com.packtpub.springrest.model.RoomCategory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link BookingService} implementation.
 *
 * @author Ludovic Dewailly
 */
@Component
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private InventoryService inventoryService;

    @Override
    @Transactional
    public BookingResponse book(BookingRequest request) {
        Booking booking = new Booking();
        Room room = inventoryService.getRoom(request.getRoomId());
        booking.setRoomId(room.getId());
        booking.setCategoryId(room.getRoomCategory().getId());
        booking.setFrom(request.getDateRange().getFrom());
        booking.setUntil(request.getDateRange().getUntil());
        booking.setCustomerName(request.getCustomerName());
        sessionFactory.getCurrentSession().save(booking);
        return new BookingResponse(Status.BOOKED, booking.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Booking getBooking(long bookingId) {
        if (bookingId <= 0) {
            throw new IllegalArgumentException("Invalid booking ID. It must be greater than zero");
        }
        Booking booking = (Booking) sessionFactory.getCurrentSession().get(Booking.class, bookingId);
        if (booking == null) {
            throw new RecordNotFoundException("No booking with ID " + bookingId);
        }
        return booking;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getBookings(DateRange dateRange) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Booking.class)
                .add(Restrictions.ge("from", dateRange.getFrom()))
                .add(Restrictions.le("until", dateRange.getUntil()))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getBookings(DateRange dateRange, RoomCategory category) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Booking.class)
                .add(Restrictions.eq("categoryId", category.getId()))
                .add(Restrictions.ge("from", dateRange.getFrom()))
                .add(Restrictions.le("until", dateRange.getUntil()))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public Booking getLastUpdatedBooking(DateRange dateRange) {
        List<Booking> bookings = sessionFactory.getCurrentSession()
                .createCriteria(Booking.class)
                .add(Restrictions.ge("from", dateRange.getFrom()))
                .add(Restrictions.le("until", dateRange.getUntil()))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setMaxResults(1)
                .addOrder(Order.desc("updatedAt"))
                .list();
        return bookings.isEmpty() ? null : bookings.get(0);
    }

    @Override
    public Booking getLastUpdatedBooking(DateRange dateRange, RoomCategory category) {
        List<Booking> bookings = sessionFactory.getCurrentSession()
                .createCriteria(Booking.class)
                .add(Restrictions.eq("categoryId", category.getId()))
                .add(Restrictions.ge("from", dateRange.getFrom()))
                .add(Restrictions.le("until", dateRange.getUntil()))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setMaxResults(1)
                .addOrder(Order.desc("updatedAt"))
                .list();
        return bookings.isEmpty() ? null : bookings.get(0);
    }
}
