package com.synergy.testing.repo;

import com.synergy.testing.entity.AirCompany;
import org.springframework.data.repository.CrudRepository;

public interface AirCompanyRepo extends CrudRepository<AirCompany, Long> {

    void findByName(String name);

    AirCompany findAirCompanyByName(String name);
}
