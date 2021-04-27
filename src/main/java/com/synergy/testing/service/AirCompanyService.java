package com.synergy.testing.service;

import com.synergy.testing.entity.AirCompany;

import java.util.List;

public interface AirCompanyService {

    void save(AirCompany airCompany);

    AirCompany getById(long id);

    List<AirCompany> getAll();

    void delete(long id);

}
