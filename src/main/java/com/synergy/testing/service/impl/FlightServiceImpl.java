package com.synergy.testing.service.impl;

import com.synergy.testing.entity.AirCompany;
import com.synergy.testing.entity.Flight;
import com.synergy.testing.entity.FlightStatus;
import com.synergy.testing.repo.FlightRepo;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepo flightRepo;
    private AirCompanyService airCompanyService;

    @Autowired
    public FlightServiceImpl(FlightRepo flightRepo, AirCompanyService airCompanyService) {
        this.flightRepo = flightRepo;
        this.airCompanyService = airCompanyService;
    }

    @Override
    public void save(Flight flight) {
        if (flight != null)
            flightRepo.save(flight);
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
    public void delete(long id) {
        if (flightRepo.existsById(id))
            flightRepo.deleteById(id);
    }

    @Override
    public List<Flight> findAllByAirCompanyAndFlightStatus(String companyName, FlightStatus status) {
        List<Flight> list = new ArrayList<>();
        AirCompany airCompany = airCompanyService.getAirCompanyByName(companyName);
        if (airCompany != null)
            list = flightRepo.findAllByAirCompanyIdAndFlightStatus(airCompany.getId(), status);
        return list;
    }
}
