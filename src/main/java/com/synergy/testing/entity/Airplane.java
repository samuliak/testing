package com.synergy.testing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "airplane")
public class Airplane implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;

    @NotNull
    @Positive
    @Column(name = "factory_serial_number")
    private long factorySerialNumber;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "air_company_id")
    private AirCompany airCompany;


    @JsonIgnore
    @OneToMany(mappedBy = "airplane")
    private List<Flight> numberOfFlights;

    @Positive
    @Column(name = "flight_distance")
    private int flightDistance;

    @NotNull
    @Positive
    @Column(name = "fuel_capacity")
    private double fuelCapacity;

    @NotBlank
    @Size(min = 1, max = 64)
    @Column(name = "type")
    private String type;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

}
