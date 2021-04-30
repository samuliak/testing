package com.synergy.testing.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "airplane")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;

    @NotNull
    @Positive
    @Column(name = "factory_serial_number")
    private long factorySerialNumber;


    /*  can make dependencies between tables, but then according to the conditions,
     I cannot have a "air company id" long int the table
     have a same situation in Flight entity */
//    @JoinTable(name = "air_company")
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
    @NotNull
    @Min(1)
    @Column(name = "air_company_id")
    private long airCompany;

    @NotNull
    @Column(name = "number_of_flights")
    private int numberOfFlights;

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
