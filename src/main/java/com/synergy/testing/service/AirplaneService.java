package com.synergy.testing.service;

import com.synergy.testing.entity.Airplane;

import java.util.List;

public interface AirplaneService {

    boolean save(Airplane airplane);

    Airplane getById(long id);

    List<Airplane> getAll();

    boolean delete(long id);

    boolean changeCompanyIdToAirplane(long companyId, long airplaneId);

}
