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
            new RentalStationDto(1, "Metro Moscavide", 38.7687, -9.0974, 8, 10),
            new RentalStationDto(2, "Metro Oriente", 38.7689, -9.0942, 4, 8),
            new RentalStationDto(3, "Parque das Nações Norte", 38.7715, -9.0980, 6, 10),
            new RentalStationDto(4, "IADE", 38.7633, -9.0941, 3, 6)
        );
        return ResponseEntity.ok(rentalStations);
    }
}