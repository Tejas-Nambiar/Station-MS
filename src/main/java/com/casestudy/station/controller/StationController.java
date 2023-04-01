package com.casestudy.station.controller;

import com.casestudy.station.interfaces.IServices;
import com.casestudy.station.model.dto.ResponseDTO;
import com.casestudy.station.model.dto.StationDTO;
import com.casestudy.station.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/stations")
public class StationController {

    @Autowired
    IServices iServices;

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<StationDTO>>> getAllStations(){
        List<StationDTO> stationDTOList = iServices.getAllStations();
        ResponseDTO<List<StationDTO>> responseDTO = new ResponseDTO<>();
        if(!CollectionUtils.isEmpty(stationDTOList)) {
            responseDTO = ResponseDTO.<List<StationDTO>>builder()
                    .data(stationDTOList)
                    .message(Constants.SUCCESS_MESSAGE)
                    .build();
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/findStation")
    public ResponseEntity<ResponseDTO<List<StationDTO>>> getStations(@RequestParam(value = "name", required = true, defaultValue = "") String name,
                                                                     @RequestParam(value = "code", required = true, defaultValue = "") String code){
        List<StationDTO> stationDTOList = iServices.getStationByNameCode(name, code);
        ResponseDTO<List<StationDTO>> responseDTO = new ResponseDTO<>();
        if(!CollectionUtils.isEmpty(stationDTOList)) {
            responseDTO = ResponseDTO.<List<StationDTO>>builder()
                    .data(stationDTOList)
                    .message(Constants.SUCCESS_MESSAGE)
                    .build();
        }
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/addStation")
    public ResponseEntity<ResponseDTO<StationDTO>> addStation(@RequestBody StationDTO stationDTO){
        Predicate<StationDTO> nullCheck = station -> StringUtils.isNotBlank(station.getStationName())
                && StringUtils.isNotBlank(station.getStationCode());

        ResponseDTO<StationDTO> responseDTO;

        if(!nullCheck.test(stationDTO)){
            responseDTO = ResponseDTO.<StationDTO>builder()
                    .data(stationDTO)
                    .message(Constants.BAD_OBJECT_MESSAGE)
                    .build();
            return ResponseEntity.ok(responseDTO);
        }

        StationDTO stationDTOResponse = iServices.addStation(stationDTO);
        if(stationDTOResponse != null) {
            responseDTO = ResponseDTO.<StationDTO>builder()
                    .data(stationDTOResponse)
                    .message(Constants.ADD_STATION_SUCCESS_MESSAGE)
                    .build();
        }
        else{
            responseDTO = ResponseDTO.<StationDTO>builder()
                    .message(Constants.ADD_STATION_FAILURE_MESSAGE)
                    .build();
        }
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<ResponseDTO<StationDTO>> deleteStation(@RequestParam(value = "id", required = true, defaultValue = "") int id){
        StationDTO stationDTOResponse = iServices.deleteStation(id);
        ResponseDTO<StationDTO> responseDTO;
        if(!Objects.equals(stationDTOResponse, new StationDTO())) {
            responseDTO = ResponseDTO.<StationDTO>builder()
                    .data(stationDTOResponse)
                    .message(Constants.DELETE_ID_SUCCESS_MESSAGE+" : "+id)
                    .build();
        }
        else {
            responseDTO = ResponseDTO.<StationDTO>builder()
                    .message(Constants.DELETE_ID_FAILURE_MESSAGE)
                    .build();
        }
        return ResponseEntity.ok(responseDTO);
    }
}
