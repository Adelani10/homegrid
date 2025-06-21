package com.delani.homegrid.entities;


import com.delani.homegrid.enums.ApartmentType;
import com.delani.homegrid.enums.PaymentFrequency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Apartment extends BaseEntity {

    private String title;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;
    private String country;
    private String state;
    private String city;
    private String address;
    private int bedrooms;
    private int bathrooms;
    private int toilets;
    private int parkingSpaces;
    private boolean furnished;
    private boolean hasBalcony;
    private boolean hasKitchen;

    @Enumerated(EnumType.STRING)
    private ApartmentType type;

    @Enumerated(EnumType.STRING)
    private PaymentFrequency paymentFrequency = PaymentFrequency.YEARLY;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}
