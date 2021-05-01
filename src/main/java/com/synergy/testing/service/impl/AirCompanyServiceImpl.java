package com.synergy.testing.service.impl;

import com.synergy.testing.entity.AirCompany;
import com.synergy.testing.repo.AirCompanyRepo;
import com.synergy.testing.service.AirCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AirCompanyServiceImpl implements AirCompanyService {

    private final AirCompanyRepo companyRepo;

    @Autowired
    public AirCompanyServiceImpl(AirCompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public boolean save(AirCompany airCompany) {
        if (airCompany == null)
            return false;
        companyRepo.save(airCompany);
        return true;
    }

    @Override
    public AirCompany getById(long id) {
        if (companyRepo.existsById(id))
            return companyRepo.findById(id).get();
        return null;
    }

    @Override
    public List<AirCompany> getAll() {
        return StreamSupport.stream(companyRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public boolean delete(long id) {
        if (!companyRepo.existsById(id))
            return false;
        companyRepo.deleteById(id);
        return true;
    }

    @Override
    public AirCompany getAirCompanyByName(String name) {
        return companyRepo.findAirCompanyByName(name);
    }
}
