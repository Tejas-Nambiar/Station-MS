package com.casestudy.station.services;

import com.casestudy.station.interfaces.IServices;
import com.casestudy.station.interfaces.IStationRepository;
import com.casestudy.station.model.Station;
import com.casestudy.station.model.dto.StationDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Services implements IServices {

    @Autowired
    IStationRepository iStationRepository;

    @Override
    public List<StationDTO> getAllStations() {
        return iStationRepository.getAllStations();
    }

    @Override
    public StationDTO addStation(StationDTO stationDTO) {
        Station station = Station.builder()
                .stationCode(stationDTO.getStationCode())
                .stationName(stationDTO.getStationName())
                .build();
        station = iStationRepository.save(station);
        return StationDTO.builder()
                .id(station.getId())
                .stationName(station.getStationName())
                .stationCode(station.getStationCode())
                .build();
    }

    @Override
    public List<StationDTO> getStationByNameCode(String name, String code)  {
        if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(code)) {
            return iStationRepository.getAllStationByNameCode(name, code);
        }
        else if(StringUtils.isNotBlank(name) && StringUtils.isBlank(code)) {
            return iStationRepository.getAllStationByName(name);
        }
        else if(StringUtils.isBlank(name) && StringUtils.isNotBlank(code)) {
            return iStationRepository.getAllStationByCode(code);
        }
        else
            return new ArrayList<>();
    }

    @Override
    public StationDTO deleteStation(int id) {
        Optional<StationDTO> findStationById = Optional.of(iStationRepository.findStationById(id));
        StationDTO stationDeleted = new StationDTO();
        if(findStationById.isPresent()) {
            iStationRepository.deleteById(findStationById.get().getId());
            stationDeleted = findStationById.get();
        }
        return stationDeleted;
    }
}
