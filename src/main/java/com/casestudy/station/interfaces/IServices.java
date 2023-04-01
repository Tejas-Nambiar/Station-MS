package com.casestudy.station.interfaces;

import com.casestudy.station.model.dto.StationDTO;

import java.util.List;

public interface IServices {
    List<StationDTO> getAllStations();
    StationDTO addStation(StationDTO stationDTO);
    List<StationDTO> getStationByNameCode(String name, String code);

    StationDTO deleteStation(int id);
}
