package com.example.exercisedb.services;

import com.example.exercisedb.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

public interface CityService {

    /**
     * Returns the list of all cities ids
     * @param pageNum Page number - DEFAULT: 0
     * @param pageSize Page size - DEFAULT: Integer.MAX_VALUE
     * @param direction Sort direction - DEFAULT: ASC
     * @param properties Sort properties - DEFAULT: name
     * @return The list of all cities ids
     */
    List<Long> getCitiesIds(Integer pageNum, Integer pageSize, Direction direction, String... properties);

    /**
     * Returns the Longest Increasing Subsequence (LIS) list of all cities ids
     * @param pageNum Page number - DEFAULT: 0
     * @param pageSize Page size - DEFAULT: Integer.MAX_VALUE
     * @param direction Sort direction - DEFAULT: ASC
     * @param properties Sort properties - DEFAULT: name
     * @return The LIS list of all cities ids
     */
    List<Long> getIdsLIS(Integer pageNum, Integer pageSize, Direction direction, String... properties);

    /**
     * Returns the Longest Increasing Subsequence (LIS) list of all cities ids, ordered by name
     * @return The LIS list of all cities ids, ordered by name
     */
    default List<Long> getIdsLIS() {
        return getIdsLIS(null, null, null, (String[]) null);
    }

    /**
     * Returns a paginated list of cites, sorted by name ASC
     * @param pageNum Page number - DEFAULT: 0
     * @param pageSize Page size - DEFAULT: Integer.MAX_VALUE
     * @return The paginated list of cites, sorted by name ASC
     */
    Page<City> getCities(Integer pageNum, Integer pageSize);

    /**
     * Returns a paginated list of cites
     * @param pageNum Page number - DEFAULT: 0
     * @param pageSize Page size - DEFAULT: Integer.MAX_VALUE
     * @param direction Sort direction - DEFAULT: ASC
     * @param properties Sort properties - DEFAULT: name
     * @return The paginated list of cites
     */
    Page<City> getCities(Integer pageNum, Integer pageSize, Direction direction, String... properties);
}
