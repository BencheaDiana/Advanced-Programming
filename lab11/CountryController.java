package org.example.controller;

import org.example.entity.Country;
import org.example.service.CountryService;
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
@RequestMapping("/countries")
@Validated
@CrossOrigin(origins = "*")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries(
            @RequestParam(value = "includeCities", defaultValue = "false") boolean includeCities) {

        try {
            List<Country> countries;
            if (includeCities) {
                countries = countryService.getAllCountriesWithCities();
            } else {
                countries = countryService.getAllCountries();
            }

            if (countries.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(countries);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable @Min(1) Long id) {
        try {
            Optional<Country> country = countryService.getCountryById(id);
            return country.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<Country> getCountryByName(@RequestParam String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Optional<Country> country = countryService.getCountryByName(name.trim());
            return country.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/with-capitals")
    public ResponseEntity<List<Country>> getCountriesWithCapitals() {
        try {
            List<Country> countries = countryService.getCountriesWithCapitals();

            if (countries.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(countries);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@Valid @RequestBody Country country) {
        try {
            Country createdCountry = countryService.createCountry(country);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody Country country) {
        try {
            Country updatedCountry = countryService.updateCountry(id, country);
            return ResponseEntity.ok(updatedCountry);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable @Min(1) Long id) {
        try {
            countryService.deleteCountry(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/cities-count")
    public ResponseEntity<Long> getCityCount(@PathVariable @Min(1) Long id) {
        try {
            if (!countryService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            Long count = countryService.getCityCountByCountryId(id);
            return ResponseEntity.ok(count);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}