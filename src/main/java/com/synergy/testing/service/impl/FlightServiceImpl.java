package com.synergy.testing.service.impl;

import com.synergy.testing.entity.AirCompany;
import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import com.synergy.testing.repo.FlightRepo;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Validated
public class FlightServiceImpl implements FlightService {

    private final FlightRepo flightRepo;
    private final AirCompanyService airCompanyService;

    @Autowired
    public FlightServiceImpl(FlightRepo flightRepo, AirCompanyService airCompanyService) {
        this.flightRepo = flightRepo;
        this.airCompanyService = airCompanyService;
    }

    @Override
    public boolean save(@Valid Flight flight) {
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
        if (!flightRepo.existsById(id) || !FlightStatus.isPresent(status))
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

    @Override
    public List<Flight> findAllByCompleteStatusAndTime() {
        List<Flight> flightCompleted = flightRepo.findAllByFlightStatus(FlightStatus.COMPLETED);
        List<Flight> returnList;
        returnList = flightCompleted
                .stream()
                .filter(f -> differenceBetweenDateBiggerThanEstimatedFlightTime(
                        f.getCreatedAt(),
                        f.getEndedAt(),
                        f.getEstimatedFlightTime()))
                .collect(Collectors.toList());

        return returnList;
    }

    private boolean differenceBetweenDateBiggerThanEstimatedFlightTime(Date creat, Date end, Date estimated) {
        long difference_In_Time = end.getTime() - creat.getTime();

        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        if (difference_In_Days > 0)
            return true;

        return difference_In_Time > estimated.getTime();
    }
}
