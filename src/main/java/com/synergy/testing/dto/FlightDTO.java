package com.synergy.testing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDTO {

    private long id;

    private long airCompanyId;

    private long airplaneId;

    private String departureCountry;

    private String destinationCountry;

    private int distance;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Time estimatedFlightTime;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endedAt;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date delayStartedAt;

    // походу криейт не передавать,
    private Date createdAt;

    public Flight getFlight() {
        Flight flight = new Flight();
        flight.setAirplaneId(this.airplaneId);
        flight.setAirCompanyId(this.airCompanyId);
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

}
