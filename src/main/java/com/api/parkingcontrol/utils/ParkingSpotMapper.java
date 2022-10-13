package com.api.parkingcontrol.utils;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ParkingSpotMapper {

    public static ParkingSpotModel dtoToEntity(ParkingSpotDto parkingSpotDto){
        var parkingSpotmodel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotmodel);
        parkingSpotmodel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return parkingSpotmodel;
    }

    public static ParkingSpotDto entityToDto(ParkingSpotModel parkingSpotModel){
        var parkingSpotDto = new ParkingSpotDto();
        BeanUtils.copyProperties(parkingSpotModel, parkingSpotDto);
        return parkingSpotDto;
    }

}
