package com.casestudy.station.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationDTO {
    private int id;
    private String stationName;
    private String stationCode;
}
