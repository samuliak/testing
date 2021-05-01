package com.synergy.testing.controller;

import com.synergy.testing.dto.AirplaneDTO;
import com.synergy.testing.entity.Airplane;
import com.synergy.testing.service.AirCompanyService;
import com.synergy.testing.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    private final AirCompanyService airCompanyService;

    @Autowired
    public AirplaneController(AirplaneService airplaneService, AirCompanyService airCompanyService) {
        this.airplaneService = airplaneService;
        this.airCompanyService = airCompanyService;
    }


    @GetMapping("/id={airplaneId}/company/id={companyId}")
    public boolean changeCompanyIdToAirplane(
            @PathVariable("airplaneId") long airplaneId,
            @PathVariable("companyId") long companyId) {
        if (airplaneId <= 0 || companyId <= 0)
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
        if (!airplaneDTO.isValid(airCompanyService))
            return false;
        return airplaneService.save(airplaneDTO.getAirplane());
    }

}
