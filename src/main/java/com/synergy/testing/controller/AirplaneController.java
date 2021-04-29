package com.synergy.testing.controller;

import com.synergy.testing.dto.AirplaneDTO;
import com.synergy.testing.entity.Airplane;
import com.synergy.testing.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;


//        AirCompany airCompany1 = new AirCompany();
//        airCompany1.setName("AirCompany1");
//        airCompany1.setCompanyType("Type1");
//        airCompany1.setFoundedAt(new Date());
//        this.airCompanyService.save(airCompany1);
//
//        Airplane airplane = new Airplane();
//        airplane.setName("Airplane1");
//        airplane.setAirCompany(1);
//        airplane.setCreatedAt(new Date());
//        airplane.setFlightDistance(100);
//        airplane.setFuelCapacity(10.0);
//        airplane.setFactorySerialNumber(5454);
//        airplane.setNumberOfFlights(0);
//        airplane.setType("type1");
//        this.airplaneService.save(airplane);
    }


    @GetMapping("/id={airplaneId}/company/id={companyId}")
    public boolean changeCompanyIdToAirplane(
            @PathVariable("airplaneId") long airplaneId,
            @PathVariable("companyId") long companyId) {
        if (airplaneId <= 0 || companyId < 0)
            return false;
        return airplaneService.changeCompanyIdToAirplane(companyId, airplaneId);
    }


    @GetMapping("/id={airplaneId}")
    public Airplane getAirplaneById(@PathVariable("airplaneId") long airplaneId) {
        if (airplaneId <= 0)
            return null;
        return airplaneService.getById(airplaneId);
    }


    @PostMapping
    public boolean saveAirplane(@RequestBody AirplaneDTO airplaneDTO) {
        if (!airplaneDTO.isValid())
            return false;
        return airplaneService.save(airplaneDTO.getAirplane());
    }

}
