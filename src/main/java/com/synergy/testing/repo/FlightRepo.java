package com.synergy.testing.repo;

import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface FlightRepo extends CrudRepository<Flight, Long> {

    List<Flight> findAllByAirCompanyIdAndFlightStatus(long airCompanyId, FlightStatus flightStatus);

    List<Flight> findAllByFlightStatusAndCreatedAtBefore(FlightStatus status, Date date);

    @Modifying
    @Query("UPDATE Flight f set f.flightStatus = 'ACTIVE', f.delayStartedAt =:date, f.estimatedFlightTime=:time WHERE f.id = :id")
    int setFlightStatusActive(@Param("id") long id, @Param("date") Date date, @Param("time") Time time);

    // need more information about fields
    @Modifying
    @Query("UPDATE Flight f set f.flightStatus = 'DELAYED', f.delayStartedAt =:date WHERE f.id = :id")
    int setFlightStatusDelayed(@Param("id") long id, @Param("date") Date date);

    @Modifying
    @Query("UPDATE Flight f set f.flightStatus = 'COMPLETED', f.endedAt =:date WHERE f.id = :id")
    int setFlightStatusCompleted(@Param("id") long id, @Param("date") Date date);
}
