package com.synergy.testing.repo;

import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface FlightRepo extends CrudRepository<Flight, Long> {

    List<Flight> findAllByAirCompanyIdAndFlightStatus(long airCompanyId, FlightStatus flightStatus);

    List<Flight> findAllByFlightStatusAndCreatedAtBefore(FlightStatus status, Date date);
}
