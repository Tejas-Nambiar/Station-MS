package com.casestudy.station.interfaces;

import com.casestudy.station.model.Station;
import com.casestudy.station.model.dto.StationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStationRepository extends JpaRepository<Station,Integer> {
    @Query(value = "SELECT new com.casestudy.station.model.dto.StationDTO(s.id, s.stationName as stationName, s.stationCode as stationCode)" +
            " FROM Station s" +
            " ORDER BY s.id")
    public List<StationDTO> getAllStations();

    @Query(value = "SELECT new com.casestudy.station.model.dto.StationDTO(s.id, s.stationName as stationName, s.stationCode as stationCode) " +
            "FROM Station s " +
            "WHERE s.stationName = :name and s.stationCode = :code " +
            "ORDER BY s.id")
    public List<StationDTO> getAllStationByNameCode(@Param("name") String name, @Param("code") String code);

    @Query(value = "SELECT new com.casestudy.station.model.dto.StationDTO(s.id, s.stationName as stationName, s.stationCode as stationCode)" +
            " FROM Station s" +
            " WHERE s.stationName = :name" +
            " ORDER BY s.id")
    public List<StationDTO> getAllStationByName(@Param("name") String name);

    @Query(value = "SELECT new com.casestudy.station.model.dto.StationDTO(s.id, s.stationName as stationName, s.stationCode as stationCode)" +
            " FROM Station s" +
            " WHERE s.stationCode = :code" +
            " ORDER BY s.id")
    public List<StationDTO> getAllStationByCode(@Param("code") String code);

    @Query(value = "SELECT new com.casestudy.station.model.dto.StationDTO(s.id, s.stationName as stationName, s.stationCode as stationCode)" +
            " FROM Station s" +
            " WHERE s.id = :id " +
            " ORDER BY s.id")
    public StationDTO findStationById(@Param("id") int id);

}
