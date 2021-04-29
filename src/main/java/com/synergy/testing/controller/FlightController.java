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


    /**
     * 3) Endpoint to find all Air Company Flights by status (use company name for identification of Air Company).
     *
     * @param companyName - air company name
     * @param status      - flight status (ACTIVE, COMPLETED, DELAYED, PENDING)
     * @return flight list
     */
    @GetMapping("/company/name={companyName}/status={status}")
    public List<Flight> getFlightsByCompanyNameAndStatus(
            @PathVariable("companyName") String companyName,
            @PathVariable("status") String status) {

        return flightService.findAllByAirCompanyAndFlightStatus(companyName, FlightStatus.valueOf(status));
    }


    /**
     * 4) Endpoint to find all Flights in ACTIVE status and started more than 24 hours ago.
     *
     * @return flight list
     */
    @GetMapping("/active")
    public List<Flight> getAllByActiveStatus() {
        return flightService.findAllByStatusAndStartedMoreThanDay(FlightStatus.ACTIVE);
    }


    /**
     * 6) Endpoint to add new Flight (set status to PENDING)
     *
     * @param flightDTO - dto for json parse
     * @return boolean successfully
     */
    @PostMapping
    public boolean saveFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.save(flightDTO.getFlight());
    }


    /**
     * 7) Endpoint to change Flight status:
     * if status to change is DELAYED – set delay started at
     * if status to change is ACTIVE – set started at
     * if status to change is COMPLETED set ended at
     *
     * @param id     - flight id
     * @param status - flight status
     * @return boolean successfully
     */
    @GetMapping("/id={id}/status={status}")
    public boolean changeFlightStatus(@PathVariable("id") long id, @PathVariable("status") FlightStatus status) {
        return flightService.changeFlightStatus(id, status);
    }

    @GetMapping("/endpoint8")
    public List<Flight> findAllByCompletedStatusAndTime() {
        return flightService.findAllByCompleteStatusAndTime();
    }

}
