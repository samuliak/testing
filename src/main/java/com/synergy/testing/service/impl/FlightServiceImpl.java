package com.synergy.testing.service.impl;

import com.synergy.testing.entity.AirCompany;
import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import com.synergy.testing.repo.FlightRepo;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.FlightService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepo flightRepo;
    private final AirCompanyService airCompanyService;

    @Autowired
    public FlightServiceImpl(FlightRepo flightRepo, AirCompanyService airCompanyService) {
        this.flightRepo = flightRepo;
        this.airCompanyService = airCompanyService;
    }

    @Override
    public boolean save(Flight flight) {
        if (flight == null)
            return false;

        flightRepo.save(flight);
        return true;
    }

    @Override
    public Flight getById(long id) {
        if (flightRepo.existsById(id))
            return flightRepo.findById(id).get();
        return null;
    }

    @Override
    public List<Flight> getAll() {
        return StreamSupport.stream(flightRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public boolean delete(long id) {
        if (!flightRepo.existsById(id))
            return false;
        flightRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Flight> findAllByAirCompanyAndFlightStatus(String companyName, FlightStatus status) {
        List<Flight> list = new ArrayList<>();
        AirCompany airCompany = airCompanyService.getAirCompanyByName(companyName);
        if (airCompany != null)
            list = flightRepo.findAllByAirCompanyIdAndFlightStatus(airCompany.getId(), status);
        return list;
    }

    @Override
    public List<Flight> findAllByStatusAndStartedMoreThanDay(FlightStatus status) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, -23);
        return flightRepo.findAllByFlightStatusAndCreatedAtBefore(status, calendar.getTime());
    }

    @Override
    public boolean changeFlightStatus(long id, FlightStatus status) {
        if (!flightRepo.existsById(id) || !Arrays.asList(FlightStatus.values()).contains(status))
            return false;

        Flight flight = flightRepo.findById(id).get();
        int queryExecute = 0;

        if (status == FlightStatus.ACTIVE) {
            Date dateCreated = new Date();
            long timeInSec = (long) ((double) flight.getDistance() / (double) 700 * 3600);
            int sec = (int) (timeInSec % 60);
            int hour = (int) (timeInSec / 60);
            int min = hour % 60;
            hour = hour / 60;
            Time time = new Time(hour, min, sec);
            queryExecute = flightRepo.setFlightStatusActive(id, dateCreated, time);
        }

        if (status == FlightStatus.DELAYED)
            queryExecute = flightRepo.setFlightStatusDelayed(id, new Date());

        if (status == FlightStatus.COMPLETED)
            queryExecute = flightRepo.setFlightStatusCompleted(id, new Date());

        return queryExecute > 0;
    }

}
