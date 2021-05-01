package com.synergy.testing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "flight")
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status")
    private FlightStatus flightStatus;

    @ManyToOne
    @JoinColumn(name = "air_company_id")
    private AirCompany airCompany;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

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
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @Column(name = "delay_started_at")
    private Date delayStartedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

}
