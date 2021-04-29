package com.synergy.testing.controller;

import com.synergy.testing.dto.FlightDTO;
import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import com.synergy.testing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
//        Flight flight = new Flight();
//        flight.setCreatedAt(new Date());
//        flight.setFlightStatus(FlightStatus.COMPLETED);
//        flight.setAirCompanyId(1);
//        flight.setAirplaneId(1);
//        flight.setEstimatedFlightTime(new Date());
//        flight.setDelayStartedAt(new Date());
//        flight.setDepartureCountry("country1");
//        flight.setDestinationCountry("country2");
//        flight.setDistance(1000);
//        flight.setEndedAt(new Date());
//        this.flightService.save(flight);
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
        if (!flightDTO.isValid())
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
