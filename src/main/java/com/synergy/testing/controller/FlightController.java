package com.synergy.testing.controller;

import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import com.synergy.testing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
        Flight flight = new Flight();
        flight.setCreatedAt(new Date());
        flight.setFlightStatus(FlightStatus.COMPLETED);
        flight.setAirCompanyId(1);
        flight.setAirplaneId(1);
        flight.setEstimatedFlightTime(new Date());
        flight.setDelayStartedAt(new Date());
        flight.setDepartureCountry("country1");
        flight.setDestinationCountry("country2");
        flight.setDistance(1000);
        flight.setEndedAt(new Date());
        this.flightService.save(flight);
    }


    @GetMapping("/company/name={companyName}/status={status}")
    public List<Flight> getListFlightsByCompanyNameAndStatus(
            @PathVariable("companyName") String companyName,
            @PathVariable("status") String status) {

        return flightService.findAllByAirCompanyAndFlightStatus(companyName, FlightStatus.valueOf(status));
    }

}
