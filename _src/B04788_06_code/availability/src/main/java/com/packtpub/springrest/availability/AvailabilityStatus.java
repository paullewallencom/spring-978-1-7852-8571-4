package com.packtpub.springrest.availability;

import com.packtpub.springrest.model.Room;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * Captures the availability status for a day.
 *
 * @author Ludovic Dewailly
 */
public class AvailabilityStatus {

    private final Date date;
    private final List<Room> rooms;

    public AvailabilityStatus(Date date, List<Room> rooms) {
        this.date = date;
        this.rooms = rooms;
    }

    public Date getDate() {
        return date;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
