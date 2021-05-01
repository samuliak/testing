package com.synergy.testing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "air_company")
public class AirCompany implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(name = "company_type")
    private String companyType;

    @Column(name = "founded_at")
    private Date foundedAt;

    @OneToMany(mappedBy = "airCompany")
    private Set<Airplane> airplanes;

    @JsonIgnore
    @OneToMany(mappedBy = "airCompany", fetch = FetchType.LAZY)
    private Set<Flight> flights;
}
