package com.casestudy.station.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "station", schema = "case_study")
public class Station {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "station_name", nullable = false)
    private String stationName;
    @Column(name = "station_code", nullable = false)
    private String stationCode;
}
