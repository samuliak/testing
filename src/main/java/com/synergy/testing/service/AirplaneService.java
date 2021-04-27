package com.synergy.testing.service;

import com.synergy.testing.entity.Airplane;

import java.util.List;

public interface AirplaneService {

    void save(Airplane airplane);

    Airplane getById(long id);

    List<Airplane> getAll();

    void delete(long id);

    boolean changeCompanyIdToAirplane(long companyId, long airplaneId);

}
