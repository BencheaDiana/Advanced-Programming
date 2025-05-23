package org.example.controller;

import org.example.entity.City;
import org.example.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
@Validated
@CrossOrigin(origins = "*")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        try {
            List<City> cities = cityService.getAllCities();

            if (cities.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(cities);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable @Min(1) Long id) {
        try {
            Optional<City> city = cityService.getCityById(id);
            return city.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<City>> getCitiesByCountryId(@PathVariable @Min(1) Long countryId) {
        try {
            List<City> cities = cityService.getCitiesByCountryId(countryId);

            if (cities.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(cities);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<City>> searchCitiesByName(@RequestParam String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            List<City> cities = cityService.searchCitiesByName(name.trim());

            if (cities.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(cities);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/capitals")
    public ResponseEntity<List<City>> getCapitalCities() {
        try {
            List<City> capitals = cityService.getCapitalCities();

            if (capitals.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(capitals);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/in-range")
    public ResponseEntity<List<City>> getCitiesInRange(@RequestParam Double minLat, @RequestParam Double maxLat, @RequestParam Double minLon, @RequestParam Double maxLon) {
        try {
            if (minLat < -90 || maxLat > 90 || minLon < -180 || maxLon > 180 ||
                    minLat > maxLat || minLon > maxLon) {
                return ResponseEntity.badRequest().build();
            }

            List<City> cities = cityService.findCitiesInCoordinateRange(minLat, maxLat, minLon, maxLon);

            if (cities.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(cities);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<City> createCity(@Valid @RequestBody City city, @RequestParam @Min(1) Long countryId) {
        try {
            City createdCity = cityService.createCity(city, countryId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable @Min(1) Long id, @Valid @RequestBody City city, @RequestParam(required = false) @Min(1) Long countryId) {
        try {
            City updatedCity = cityService.updateCity(id, city, countryId);
            return ResponseEntity.ok(updatedCity);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable @Min(1) Long id) {
        try {
            cityService.deleteCity(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/distance")
    public ResponseEntity<Double> calculateDistance(@RequestParam @Min(1) Long city1, @RequestParam @Min(1) Long city2) {
        try {
            if (city1.equals(city2)) {
                return ResponseEntity.ok(0.0);
            }

            double distance = cityService.calculateDistance(city1, city2);
            return ResponseEntity.ok(distance);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}