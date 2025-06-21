package com.delani.homegrid.enums;

import lombok.Getter;

@Getter
public enum PaymentFrequency {
    MONTHLY("Monthly"),
    QUARTERLY("Quarterly"),
    SEMI_ANNUAL("Every 6 Months"),
    YEARLY("Yearly");

    private final String label;

    PaymentFrequency(String label) {
        this.label = label;
    }

}

