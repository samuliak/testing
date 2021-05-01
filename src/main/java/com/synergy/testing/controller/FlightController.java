package com.synergy.testing.controller;

import com.synergy.testing.dto.FlightDTO;
import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.AirplaneService;
import com.synergy.testing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;
    private final AirCompanyService airCompanyService;
    private final AirplaneService airplaneService;

    @Autowired
    public FlightController(
            FlightService flightService,
            AirCompanyService airCompanyService,
            AirplaneService airplaneService) {
        this.flightService = flightService;
        this.airCompanyService = airCompanyService;
        this.airplaneService = airplaneService;
    }


    @GetMapping("/company/name={companyName}/status={status}")
    public List<Flight> getFlightsByCompanyNameAndStatus(
            @PathVariable("companyName") String companyName,
            @PathVariable("status") FlightStatus status) {
        if (companyName.isBlank() || companyName.length() > 64 || !FlightStatus.isPresent(status))
            return null;
        return flightService.findAllByAirCompanyAndFlightStatus(companyName, status);
    }


    @GetMapping("/active")
    public List<Flight> getAllByActiveStatus() {
        return flightService.findAllByStatusAndStartedMoreThanDay(FlightStatus.ACTIVE);
    }


    @PostMapping
    public boolean saveFlight(@RequestBody FlightDTO flightDTO) {
        if (!flightDTO.isValid(airplaneService, airCompanyService))
            return false;
        return flightService.save(flightDTO.getFlight());
    }


    @GetMapping("/id={id}/status={status}")
    public boolean changeFlightStatus(@PathVariable("id") long id, @PathVariable("status") FlightStatus status) {
        if (id <= 0 || !FlightStatus.isPresent(status))
            return false;
        return flightService.changeFlightStatus(id, status);
    }


    @GetMapping("/endpoint8")
    public List<Flight> findAllByCompletedStatusAndTime() {
        return flightService.findAllByCompleteStatusAndTime();
    }

}
