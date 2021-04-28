package com.synergy.testing.service;

import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FlightService {

    boolean save(Flight flight);

    Flight getById(long id);

    List<Flight> getAll();

    boolean delete(long id);

    List<Flight> findAllByAirCompanyAndFlightStatus(String companyName, FlightStatus status);

    List<Flight> findAllByStatusAndStartedMoreThanDay(FlightStatus status);

    @Transactional
    boolean changeFlightStatus(long id, FlightStatus status);
}
