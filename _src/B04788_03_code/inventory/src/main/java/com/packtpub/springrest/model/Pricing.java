package com.packtpub.springrest.model;

import javax.persistence.*;

/**
 * Captures pricing information for room categories.
 *
 * @author Ludovic Dewailly
 */
@Entity(name = "pricings")
public class Pricing {

    private long id;
    private PricingModel pricingModel;
    private Double priceGuest1;
    private Double priceGuest2;
    private Double priceGuest3;

    private Pricing() {
        // required for Hibernate
    }

    public Pricing(PricingModel pricingModel) {
        if (pricingModel == null) {
            throw new IllegalArgumentException("pricingModel");
        }
        this.pricingModel = pricingModel;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public PricingModel getPricingModel() {
        return pricingModel;
    }

    public void setPricingModel(PricingModel pricingModel) {
        if (pricingModel == null) {
            throw new IllegalArgumentException("pricingModel");
        }
        this.pricingModel = pricingModel;
    }

    @Column(name = "price_guest_1", nullable = false)
    public Double getPriceGuest1() {
        return priceGuest1;
    }

    public void setPriceGuest1(Double priceGuest1) {
        this.priceGuest1 = priceGuest1;
    }

    @Column(name = "price_guest_2")
    public Double getPriceGuest2() {
        return priceGuest2;
    }

    public void setPriceGuest2(Double priceGuest2) {
        this.priceGuest2 = priceGuest2;
    }

    @Column(name = "price_guest_3")
    public Double getPriceGuest3() {
        return priceGuest3;
    }

    public void setPriceGuest3(Double priceGuest3) {
        this.priceGuest3 = priceGuest3;
    }
}
