package com.synergy.testing.service;

import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FlightService {

    /**
     * 6) Endpoint to add new Flight (set status to PENDING)
     *
     * @param flight - object for save
     * @return boolean successfully
     */
    boolean save(Flight flight);

    Flight getById(long id);

    List<Flight> getAll();

    boolean delete(long id);

    /**
     * 3) Endpoint to find all Air Company Flights by status (use company name for identification of Air Company).
     *
     * @param companyName - air company name
     * @param status      - flight status (ACTIVE, COMPLETED, DELAYED, PENDING)
     * @return flight list
     */
    List<Flight> findAllByAirCompanyAndFlightStatus(String companyName, FlightStatus status);

    /**
     * 4) Endpoint to find all Flights in ACTIVE status and started more than 24 hours ago.
     *
     * @return flight list
     */
    List<Flight> findAllByStatusAndStartedMoreThanDay(FlightStatus status);


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
    @Transactional
    boolean changeFlightStatus(long id, FlightStatus status);

    /**
     * Endpoint 8 - work
     *
     * @return flight list
     */
    List<Flight> findAllByCompleteStatusAndTime();

}
