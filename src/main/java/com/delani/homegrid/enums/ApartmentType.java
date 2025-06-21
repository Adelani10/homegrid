package com.delani.homegrid.enums;

import lombok.Getter;

@Getter
public enum ApartmentType {
    SELF_CONTAIN("Self Contain"),
    SINGLE_ROOM("Single Room"),
    MINI_FLAT("Mini Flat"),
    FLAT("Flat"),
    DUPLEX("Duplex"),
    PENTHOUSE("Penthouse"),
    STUDIO("Studio Apartment"),
    SHARED("Shared Apartment");

    private final String label;

    ApartmentType(String label) {
        this.label = label;
    }

}

