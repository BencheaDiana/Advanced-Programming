package org.example.service;

import org.example.entity.Country;
import org.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public List<Country> getAllCountriesWithCities() {
        return countryRepository.findAllWithCities();
    }

    @Transactional(readOnly = true)
    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Country> getCountryByName(String name) {
        return countryRepository.findByNameIgnoreCase(name);
    }

    public Country createCountry(Country country) {
        if (countryRepository.existsByNameIgnoreCase(country.getName())) {
            throw new IllegalArgumentException("Country with name '" + country.getName() + "' already exists");
        }
        return countryRepository.save(country);
    }

    public Country updateCountry(Long id, Country updatedCountry) {
        return countryRepository.findById(id)
                .map(country -> {
                    if (!country.getName().equalsIgnoreCase(updatedCountry.getName()) &&
                            countryRepository.existsByNameIgnoreCase(updatedCountry.getName())) {
                        throw new IllegalArgumentException("Country with name '" + updatedCountry.getName() + "' already exists");
                    }
                    country.setName(updatedCountry.getName());
                    return countryRepository.save(country);
                })
                .orElseThrow(() -> new IllegalArgumentException("Country with id " + id + " not found"));
    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new IllegalArgumentException("Country with id " + id + " not found");
        }
        countryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Country> getCountriesWithCapitals() {
        return countryRepository.findCountriesWithCapitals();
    }

    @Transactional(readOnly = true)
    public Long getCityCountByCountryId(Long countryId) {
        return countryRepository.countCitiesByCountryId(countryId);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return countryRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return countryRepository.existsByNameIgnoreCase(name);
    }
}