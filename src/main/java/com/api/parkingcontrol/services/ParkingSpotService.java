package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.dtos.ParkingSpotUpdateDto;
import com.api.parkingcontrol.exceptions.InvalidSpotException;
import com.api.parkingcontrol.entity.ParkingSpot;
import com.api.parkingcontrol.exceptions.SpotNotFoundException;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import com.api.parkingcontrol.utils.ParkingSpotMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingSpotService {


    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }


    @Transactional
    public ParkingSpot save(ParkingSpot parkingSpotmodel) {
        return parkingSpotRepository.save(parkingSpotmodel);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public List<ParkingSpotDto> findAll() {
        return parkingSpotRepository.findAll().stream().map(ParkingSpotMapper::entityToDto).collect(Collectors.toList());
    }

    private ParkingSpot findById(UUID id) throws SpotNotFoundException {
        return parkingSpotRepository.findById(id).orElseThrow(() -> new SpotNotFoundException("Parking Spot não encontrado pelo id " + id.toString()));
    }

    @Transactional
    public void delete(ParkingSpot parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }

//    @PostMapping
    public ParkingSpotDto saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) throws InvalidSpotException {

        if (existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            throw new InvalidSpotException("Conflict: License Plate Car is already in use!");
            //return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }
        if (existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            throw new InvalidSpotException("Conflict: Parking Spot is already in use!");
            //return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        if (existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            throw new InvalidSpotException("Conflict: Parking Spot already registered for this apartment/block!");
            //return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!");
        }

        ParkingSpot model = save(ParkingSpotMapper.dtoToEntity(parkingSpotDto));
        return ParkingSpotMapper.entityToDto(model);
    }

//    @GetMapping
    public ParkingSpotDto getOneParkingSpot(@PathVariable(value = "id") UUID id) throws SpotNotFoundException {
        ParkingSpot parkingSpotModelOptional = findById(id);
        return ParkingSpotMapper.entityToDto(parkingSpotModelOptional);
    }

//    @DeleteMapping
    public void deleteParkingSpot(@PathVariable(value = "id") UUID id) throws SpotNotFoundException {
        delete(findById(id));
    }

//    @PutMapping
    public ParkingSpotDto updateParkingSpot(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid ParkingSpotUpdateDto parkingSpotDto) throws SpotNotFoundException {
        ParkingSpot entity = ParkingSpotMapper.updateEntity(parkingSpotDto, findById(id));
        return ParkingSpotMapper.entityToDto(save(entity));
    }
}
