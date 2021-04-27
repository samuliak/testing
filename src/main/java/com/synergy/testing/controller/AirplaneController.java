package com.synergy.testing.controller;

import com.synergy.testing.entity.AirCompany;
import com.synergy.testing.entity.Airplane;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AirplaneController {

    private final AirplaneService airplaneService;
    private final AirCompanyService airCompanyService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService, AirCompanyService airCompanyService) {
        this.airplaneService = airplaneService;
        this.airCompanyService = airCompanyService;

        AirCompany airCompany1 = new AirCompany();
        airCompany1.setName("AirCompany1");
        airCompany1.setCompanyType("Type1");
        airCompany1.setFoundedAt(new Date());
        this.airCompanyService.save(airCompany1);

        Airplane airplane = new Airplane();
        airplane.setName("Airplane1");
        airplane.setAirCompany(1);
        airplane.setCreatedAt(new Date());
        airplane.setFlightDistance(100);
        airplane.setFuelCapacity(10.0);
        airplane.setFactorySerialNumber(5454);
        airplane.setNumberOfFlights(0);
        airplane.setType("type1");
        this.airplaneService.save(airplane);
    }


    /**
     * Endpoint to move airplanes between companies (simple endpoint to reassign airplane to another company)
     *
     * @param airplaneId - airplane`s identifier
     * @param companyId - companies`s identifier
     * @return
     */
    @GetMapping(value = "/airplane/{airplaneId}/company/{companyId}")
    public boolean changeCompanyIdToAirplane(
            @PathVariable("airplaneId") long airplaneId,
            @PathVariable("companyId") long companyId) {

        return airplaneService.changeCompanyIdToAirplane(companyId, airplaneId);
    }

    /**
     * Get airplane by ID
     *
     * @param airplaneId
     * @return
     */
    @GetMapping(value = "/airplane/{airplaneId}")
    public Airplane getAirplaneById(@PathVariable("airplaneId") long airplaneId) {
        return airplaneService.getById(airplaneId);
    }
}
