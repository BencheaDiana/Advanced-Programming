package org.example.repository;

import org.example.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByCountryId(Long countryId);

    @Query("SELECT c FROM City c WHERE c.country.name = :countryName")
    List<City> findByCountryName(@Param("countryName") String countryName);

    List<City> findByCapitalTrue();

    List<City> findByCapitalFalse();

    List<City> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM City c WHERE c.latitude BETWEEN :minLat AND :maxLat AND c.longitude BETWEEN :minLon AND :maxLon")
    List<City> findCitiesInCoordinateRange(@Param("minLat") Double minLatitude, @Param("maxLat") Double maxLatitude, @Param("minLon") Double minLongitude, @Param("maxLon") Double maxLongitude);

    List<City> findAllByOrderByNameAsc();

    List<City> findByCountryIdOrderByNameAsc(Long countryId);

    boolean existsByNameAndCountryId(String name, Long countryId);
}
