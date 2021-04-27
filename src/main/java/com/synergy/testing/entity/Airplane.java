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
@Table(name = "airplane")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false, updatable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "factory_serial_number")
    private long factorySerialNumber;


    /*  can make dependencies between tables, but then according to the conditions,
     I cannot have a "air company id" long int the table
     have a same situation in Flight entity */
//    @JoinTable(name = "air_company")
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "air_id")
    @Column(name = "air_company_id")
    private long airCompany;

    @Column(name = "number_of_flights")
    private int numberOfFlights;

    @Column(name = "flight_distance")
    private int flightDistance;

    @Column(name = "fuel_capacity")
    private double fuelCapacity;

    @Column(name = "type")
    private String type;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

}
