package com.synergy.testing.entity;

import java.util.Arrays;

public enum FlightStatus {
    ACTIVE,
    COMPLETED,
    DELAYED,
    PENDING;
    public static boolean isPresent(FlightStatus status){
        return Arrays.asList(FlightStatus.values()).contains(status);
    }
}

