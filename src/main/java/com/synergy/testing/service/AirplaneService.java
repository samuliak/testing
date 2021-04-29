package com.synergy.testing.service;

import com.synergy.testing.entity.Airplane;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface AirplaneService {

    /**
     * 5) Endpoint to add new Airplane (Could be assign to a company immediately or moved later).
     * Method changeCompanyIdToAirplane() can use for assign company later.
     *
     * @param airplane - entity for save
     * @return - boolean, save or not
     */
    boolean save(Airplane airplane);

    /**
     * Get airplane by ID
     *
     * @param id - airplane`s identifier
     * @return airplane object
     */
    Airplane getById(long id);

    List<Airplane> getAll();

    boolean delete(long id);

    /**
     * 2. Endpoint to move airplanes between companies (simple endpoint to reassign airplane to another company)
     *
     * @param airplaneId - airplane`s identifier
     * @param companyId  - companies`s identifier
     * @return boolean result
     */
    boolean changeCompanyIdToAirplane(long companyId, long airplaneId);

}
