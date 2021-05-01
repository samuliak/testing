package com.synergy.testing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.AirplaneService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDTO {

    private AirplaneService airplaneService;
    private AirCompanyService airCompanyService;


    private long id;

    private long airCompanyId;

    private long airplaneId;

    private String departureCountry;

    private String destinationCountry;

    private int distance;

    private Time estimatedFlightTime;

    private Date endedAt;

    private Date delayStartedAt;

    private Date createdAt;

    public Flight getFlight() {
        Flight flight = new Flight();
        if (airplaneService.getById(airplaneId) != null && airCompanyService.getById(airCompanyId) != null) {
            flight.setAirplane(airplaneService.getById(airplaneId));
            flight.setAirCompany(airCompanyService.getById(airCompanyId));
        } else return null;
        flight.setFlightStatus(FlightStatus.PENDING);
        flight.setDistance(this.distance);
        flight.setDestinationCountry(this.destinationCountry);
        flight.setDepartureCountry(this.departureCountry);
        flight.setEstimatedFlightTime(this.estimatedFlightTime);
        flight.setCreatedAt(new Date());
        flight.setEndedAt(this.endedAt);
        flight.setDelayStartedAt(this.delayStartedAt);

        return flight;
    }

    public boolean isValid(AirplaneService airplaneService, AirCompanyService airCompanyService) {
        this.airplaneService = airplaneService;
        this.airCompanyService = airCompanyService;

        if (this.airCompanyId < 0)
            return false;
        if (this.airplaneId <= 0)
            return false;
        if (this.departureCountry.isBlank() || this.departureCountry.length() > 64)
            return false;
        if (this.destinationCountry.isBlank() || this.destinationCountry.length() > 64)
            return false;
        if (this.distance < 0)
            return false;

        return true;
    }

}
