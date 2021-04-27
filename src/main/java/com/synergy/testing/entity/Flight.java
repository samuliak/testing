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
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status")
    private FlightStatus flightStatus;

    @Column(name = "air_company_id")
    private int airCompanyId;

    @Column(name = "airplane_id")
    private int airplaneId;

    @Column(name = "departure_country")
    private String departureCountry;

    @Column(name = "destination_country")
    private String destinationCountry;

    @Column(name = "distance")
    private int distance;

    @Temporal(TemporalType.TIME)
    @Column(name = "estimated_flight_time")
    private Date estimatedFlightTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ended_at")
    private Date endedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delay_started_at")
    private Date delayStartedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

}
