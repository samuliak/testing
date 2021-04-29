package com.synergy.testing.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "air_company")
public class AirCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_id")
    private long id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(name = "company_type")
    private String companyType;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "founded_at")
    private Date foundedAt;

}
