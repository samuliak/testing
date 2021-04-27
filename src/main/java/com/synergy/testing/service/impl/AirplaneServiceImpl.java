package com.synergy.testing.service.impl;

import com.synergy.testing.entity.Airplane;
import com.synergy.testing.repo.AirplaneRepo;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepo airplaneRepo;
    private final AirCompanyService companyService;

    @Autowired
    public AirplaneServiceImpl(AirplaneRepo airplaneRepo, AirCompanyServiceImpl companyService) {
        this.airplaneRepo = airplaneRepo;
        this.companyService = companyService;
    }


    @Override
    public void save(Airplane airplane) {
        if (airplane != null)
            airplaneRepo.save(airplane);
    }

    @Override
    public Airplane getById(long id) {
        if (airplaneRepo.existsById(id))
            return airplaneRepo.findById(id).get();
        return null;
    }

    @Override
    public List<Airplane> getAll() {
        return StreamSupport.stream(airplaneRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        if (airplaneRepo.existsById(id))
            airplaneRepo.deleteById(id);
    }

    @Override
    public boolean changeCompanyIdToAirplane(long companyId, long airplaneId) {
        Airplane airplane = this.getById(airplaneId);
        if (companyService.getById(companyId) == null || airplane == null)
            return false;

        airplane.setAirCompany(companyId);
        this.save(airplane);
        return true;
    }
}
