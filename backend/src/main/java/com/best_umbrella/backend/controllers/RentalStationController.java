package com.best_umbrella.backend.controllers;

import com.best_umbrella.backend.dto.RentalStationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/rental-stations")
public class RentalStationController {

    @GetMapping
    public ResponseEntity<List<RentalStationDto>> listStations() {
        List<RentalStationDto> rentalStations = Arrays.asList(
            new RentalStationDto(1, "Metro Moscavide", 38.77639, -9.10169, 8, 10),
            new RentalStationDto(2, "Metro Oriente", 38.76784, -9.09935, 4, 8),
            new RentalStationDto(3, "Parque das Nações Norte", 38.76800, -9.09400, 6, 10),
            new RentalStationDto(4, "IADE", 38.7818, -9.10251, 3, 6)
        );
        return ResponseEntity.ok(rentalStations);
    }
}