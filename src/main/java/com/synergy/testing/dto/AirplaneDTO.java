package com.synergy.testing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synergy.testing.entity.Airplane;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirplaneDTO {

    private long id;

    private String name;

    private long factorySerialNumber;

    private long airCompany;

    private int numberOfFlights;

    private int flightDistance;

    private double fuelCapacity;

    private String type;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public Airplane getAirplane() {
        Airplane airplane = new Airplane();
        airplane.setName(this.name);
        airplane.setAirCompany(this.airCompany);
        airplane.setType(this.type);
        airplane.setNumberOfFlights(this.numberOfFlights);
        airplane.setFuelCapacity(this.fuelCapacity);
        airplane.setFlightDistance(this.flightDistance);
        airplane.setFactorySerialNumber(this.factorySerialNumber);
        airplane.setCreatedAt(Objects.requireNonNullElseGet(this.createdAt, Date::new));

        return airplane;
    }

    public boolean isValid() {
        if (this.name.isBlank() && this.name.length() > 128)
            return false;
        if (this.factorySerialNumber <= 0)
            return false;
        if (this.airCompany < 0)
            return false;
        if (this.numberOfFlights < 0)
            return false;
        if (this.flightDistance <= 0)
            return false;
        if (this.fuelCapacity <= 0)
            return false;
        if (this.type.isBlank() || this.type.length() > 64)
            return false;

        return true;
    }
}
