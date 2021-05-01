package com.synergy.testing.service;

import com.synergy.testing.entity.AirCompany;

import java.util.List;

public interface AirCompanyService {

    boolean save(AirCompany airCompany);

    AirCompany getById(long id);

    List<AirCompany> getAll();

    boolean delete(long id);

    AirCompany getAirCompanyByName(String name);

}
