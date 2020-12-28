package com.packtpub.springrest.booking;

import com.packtpub.springrest.DateRange;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * A booking request to be processed by the {@link BookingService}.
 *
 * @author Ludovic Dewailly
 */
public class BookingRequest {

    private final long roomId;
    private final DateRange dateRange;
    private final String customerName;

    public BookingRequest(long roomId, DateRange dateRange, String customerName) {
        this.roomId = roomId;
        this.dateRange = dateRange;
        this.customerName = customerName;
    }

    public long getRoomId() {
        return roomId;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
