package com.packtpub.springrest.booking;

import com.packtpub.springrest.DateRange;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A booking request to be processed by the {@link BookingService}.
 *
 * @author Ludovic Dewailly
 */
public class BookingRequest {

    @Min(1)
    private long roomId;
    @Valid @NotNull
    private DateRange dateRange;
    @Size(min = 1, max = 128)
    private String customerName;
    @Valid @NotNull
    private CreditCardDetails creditCardDetails;

    public BookingRequest() {}

    public BookingRequest(long roomId, DateRange dateRange, String customerName,
                          CreditCardDetails creditCardDetails) {
        this.roomId = roomId;
        this.dateRange = dateRange;
        this.customerName = customerName;
        this.creditCardDetails = creditCardDetails;
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

    public CreditCardDetails getCreditCardDetails() {
        return creditCardDetails;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
