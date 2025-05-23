package org.example.service;

import org.example.entity.City;
import org.example.entity.Country;
import org.example.repository.CityRepository;
import org.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<City> getAllCities() {
        return cityRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<City> getCitiesByCountryId(Long countryId) {
        return cityRepository.findByCountryIdOrderByNameAsc(countryId);
    }

    @Transactional(readOnly = true)
    public List<City> getCitiesByCountryName(String countryName) {
        return cityRepository.findByCountryName(countryName);
    }

    @Transactional(readOnly = true)
    public List<City> getCapitalCities() {
        return cityRepository.findByCapitalTrue();
    }

    @Transactional(readOnly = true)
    public List<City> searchCitiesByName(String name) {
        return cityRepository.findByNameContainingIgnoreCase(name);
    }

    public City createCity(City city, Long countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new IllegalArgumentException("Country with id " + countryId + " not found"));

        if (cityRepository.existsByNameAndCountryId(city.getName(), countryId)) {
            throw new IllegalArgumentException("City with name '" + city.getName() + "' already exists in " + country.getName());
        }

        city.setCountry(country);
        return cityRepository.save(city);
    }}