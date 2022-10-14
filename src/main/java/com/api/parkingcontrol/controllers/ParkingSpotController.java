package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.dtos.ParkingSpotUpdateDto;
import com.api.parkingcontrol.exceptions.InvalidSpotException;
import com.api.parkingcontrol.exceptions.SpotNotFoundException;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

//    Outra alternativa de injeção de classe
//    final ParkingSpotService parkingSpotService;
//
//    public ParkingSpotController(ParkingSpotService parkingSpotService) {
//        this.parkingSpotService = parkingSpotService;
//    }
    @Autowired
    private ParkingSpotService parkingSpotService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ParkingSpotDto saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) throws InvalidSpotException {
        return parkingSpotService.saveParkingSpot(parkingSpotDto);
    }

    @GetMapping
    public List<ParkingSpotDto> getAllParkingSpots() {
        return parkingSpotService.findAll();
    }

    @GetMapping("/{id}")
    public ParkingSpotDto getOneParkingSpot(@PathVariable(value = "id") UUID id) throws SpotNotFoundException {
        return parkingSpotService.getOneParkingSpot(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteParkingSpot(@PathVariable(value = "id") UUID id) throws SpotNotFoundException {
        parkingSpotService.deleteParkingSpot(id);
    }

    @PutMapping("/{id}")
    public ParkingSpotDto updateParkingSpot(@PathVariable(value = "id") UUID id,
                                            @RequestBody @Valid ParkingSpotUpdateDto dto) throws SpotNotFoundException {
        return parkingSpotService.updateParkingSpot(id, dto);}

}
