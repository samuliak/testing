package com.synergy.testing.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "air_company")
public class AirCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_id", insertable = false, updatable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "company_type")
    private String companyType;

    @Temporal(TemporalType.DATE)
    @Column(name = "founded_at")
    private Date foundedAt;

}
