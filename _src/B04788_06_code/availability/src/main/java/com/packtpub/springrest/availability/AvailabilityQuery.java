package com.packtpub.springrest.availability;

import com.packtpub.springrest.DateRange;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Encapsulates search criteria for room availability.
 *
 * @author Ludovic Dewailly
 */
public class AvailabilityQuery {

    private final DateRange dateRange;
    private final long categoryId;

    public AvailabilityQuery(DateRange dateRange, long categoryId) {
        if (dateRange == null) {
            throw new IllegalArgumentException("dateRange is mandatory");
        }
        this.dateRange = dateRange;
        this.categoryId = categoryId;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public long getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
