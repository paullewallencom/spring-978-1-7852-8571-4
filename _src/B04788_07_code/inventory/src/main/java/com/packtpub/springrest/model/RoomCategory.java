package com.packtpub.springrest.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;

/**
 * This class represents a type of {@link Room}. Each room belongs to one room category.
 *
 * @author Ludovic Dewailly
 */
@Entity(name = "room_categories")
public class RoomCategory {

    private long id;
    private String name;
    private String description;
    private Pricing pricing;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 2048)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
