package com.synergy.testing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synergy.testing.entity.Airplane;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.FlightService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirplaneDTO {

    private AirCompanyService airCompanyService;

    private long id;

    private String name;

    private long factorySerialNumber;

    private long airCompany;

    private int flightDistance;

    private double fuelCapacity;

    private String type;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public Airplane getAirplane() {
        Airplane airplane = new Airplane();
        airplane.setName(this.name);
        if (airCompanyService.getById(airCompany) != null || airCompany >= 0)
            airplane.setAirCompany(airCompanyService.getById(airCompany));
        else return null;
        airplane.setType(this.type);
        airplane.setFuelCapacity(this.fuelCapacity);
        airplane.setFlightDistance(this.flightDistance);
        airplane.setFactorySerialNumber(this.factorySerialNumber);
        airplane.setCreatedAt(Objects.requireNonNullElseGet(this.createdAt, Date::new));

        return airplane;
    }

    public boolean isValid(AirCompanyService airCompanyService) {
        this.airCompanyService = airCompanyService;
        if (this.name.isBlank() && this.name.length() > 128)
            return false;
        if (this.factorySerialNumber <= 0)
            return false;
        if (this.airCompany < 0)
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
