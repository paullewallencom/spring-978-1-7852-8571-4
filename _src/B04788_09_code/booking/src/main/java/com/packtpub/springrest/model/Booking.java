package com.packtpub.springrest.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a booking.
 *
 * @author Ludovic Dewailly
 */
@Entity(name = "bookings")
public class Booking {

    private long id;
    private long categoryId;
    private long roomId;
    private Date from;
    private Date until;
    private String customerName;
    private Date createdAt;
    private Date updatedAt;

    public Booking() {
        createdAt = new Date();
        updatedAt = createdAt;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    @Column(name = "category_id", nullable = false)
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "room_id", nullable = false)
    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "_from", nullable = false) // "from" is an SQL keyword
    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "until", nullable = false)
    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }

    @Column(name = "customer_name", nullable = false, length = 128)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(name = "created_at", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at", nullable = false)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
