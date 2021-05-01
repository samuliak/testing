package com.synergy.testing.controller;

import com.synergy.testing.dto.AirCompanyDTO;
import com.synergy.testing.entity.AirCompany;
import com.synergy.testing.service.AirCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircompany")
public class AirCompanyController {

    private final AirCompanyService service;

    @Autowired
    public AirCompanyController(AirCompanyService service) {
        this.service = service;
    }

    @PostMapping
    public boolean save(@RequestBody AirCompanyDTO airCompanyDTO) {
        if (!airCompanyDTO.isValid(service))
            return false;
        return service.save(airCompanyDTO.getAirCompany());
    }

    @PostMapping("/{id}")
    public boolean delete(@PathVariable("id") long id) {
        if (id <= 0)
            return false;
        return service.delete(id);
    }

    @GetMapping("/id={airCompanyId}")
    public AirCompany getById(@PathVariable("airCompanyId") long id) {
        if (id <= 0)
            return null;
        return service.getById(id);
    }

    @GetMapping("/name={airName}")
    public AirCompany getByName(@PathVariable("airName") String name) {
        if (name.isBlank() || name.length() > 128)
            return null;
        return service.getAirCompanyByName(name);
    }

    @GetMapping("/all")
    public List<AirCompany> getAll() {
        return service.getAll();
    }


}
