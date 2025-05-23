package org.example.repository;

import org.example.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

    List<Country> findAllByOrderByNameAsc();

    @Query("SELECT DISTINCT c FROM Country c LEFT JOIN FETCH c.cities")
    List<Country> findAllWithCities();

    @Query("SELECT DISTINCT c FROM Country c JOIN c.cities city WHERE city.capital = true")
    List<Country> findCountriesWithCapitals();

    @Query("SELECT COUNT(city) FROM City city WHERE city.country.id = :countryId")
    Long countCitiesByCountryId(Long countryId);
}