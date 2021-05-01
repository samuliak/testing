package com.synergy.testing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synergy.testing.entity.AirCompany;
import com.synergy.testing.service.AirCompanyService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class AirCompanyDTO {

    private AirCompanyService airCompanyService;

    private String name;
    private String companyType;

    private Date foundedAt;

    public AirCompany getAirCompany() {
        AirCompany airCompany = new AirCompany();
        airCompany.setName(this.name);
        airCompany.setCompanyType(this.companyType);
        airCompany.setFoundedAt(this.foundedAt);

        return airCompany;
    }

    public boolean isValid(AirCompanyService airCompanyService) {
        this.airCompanyService = airCompanyService;
        if (this.name.isBlank() && this.name.length() > 128 && airCompanyService.getAirCompanyByName(this.name) != null)
            return false;
        if (this.companyType.isBlank() && this.companyType.length() > 65)
            return false;

        return true;
    }
}
