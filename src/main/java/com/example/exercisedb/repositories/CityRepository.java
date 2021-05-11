package com.example.exercisedb.repositories;

import com.example.exercisedb.entities.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    /**
     * Returns the list of all cites ids
     * @param pageable Page request for elements
     * @return The list of all cites ids
     */
    @Query("SELECT id FROM City")
    List<Long> findAllIds(Pageable pageable);
}
