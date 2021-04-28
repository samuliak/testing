package com.synergy.testing.service;

import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightService {

    void save(Flight flight);

    Flight getById(long id);

    List<Flight> getAll();

    void delete(long id);

    List<Flight> findAllByAirCompanyAndFlightStatus(String companyName, FlightStatus status);

}
