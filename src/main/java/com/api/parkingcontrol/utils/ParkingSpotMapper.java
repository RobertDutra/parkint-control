package com.api.parkingcontrol.utils;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.dtos.ParkingSpotUpdateDto;
import com.api.parkingcontrol.entity.ParkingSpot;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ParkingSpotMapper {

    public static ParkingSpot dtoToEntity(ParkingSpotDto parkingSpotDto){
        var parkingSpotmodel = new ParkingSpot();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotmodel);
        parkingSpotmodel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return parkingSpotmodel;
    }

    public static ParkingSpotDto entityToDto(ParkingSpot parkingSpotModel){
        var parkingSpotDto = new ParkingSpotDto();
        BeanUtils.copyProperties(parkingSpotModel, parkingSpotDto);
        return parkingSpotDto;
    }

    public static ParkingSpot updateEntity(ParkingSpotUpdateDto dto, ParkingSpot entity){
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
