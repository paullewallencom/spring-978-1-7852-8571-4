package com.packtpub.springrest.client;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Room representation for the client library.
 *
 * @author Ludovic Dewailly
 */
public class Room {

    private long id;
    private long roomCategoryId;
    private String name;
    private String description;

    public long getId() {
        return id;
    }

    public long getRoomCategoryId() {
        return roomCategoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
