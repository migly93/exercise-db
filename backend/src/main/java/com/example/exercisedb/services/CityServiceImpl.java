package com.example.exercisedb.services;

import com.example.exercisedb.entities.City;
import com.example.exercisedb.repositories.CityRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.exercisedb.utilities.ArrayUtilities.getLongestIncreasingSubsequence;
import static com.example.exercisedb.utilities.QueryUtilities.createPageRequest;

@Service
@Data
public class CityServiceImpl implements CityService{

    private static final String DEFAULT_SORT_PARAMETER = "name";
    private static final Direction DEFAULT_SORT_DIRECTION = Direction.ASC;

    private final CityRepository cityRepository;

    @Override
    public List<Long> getCitiesIds(Integer pageNum, Integer pageSize, Direction direction, String... properties) {
        if(properties == null || properties.length == 0) properties = new String[] {DEFAULT_SORT_PARAMETER};
        PageRequest pageRequest = createPageRequest(pageNum, pageSize, direction, properties);
        return cityRepository.findAllIds(pageRequest);
    }

    @Override
    public List<Long> getIdsLIS(Integer pageNum, Integer pageSize, Direction direction, String... properties) {
        List<Long> ids = getCitiesIds(pageNum, pageSize, direction, properties);
        return getLongestIncreasingSubsequence(ids.toArray(new Long[0]));
    }

    @Override
    public Page<City> getCities(Integer pageNum, Integer pageSize) {
        return getCities(pageNum, pageSize, DEFAULT_SORT_DIRECTION, DEFAULT_SORT_PARAMETER);
    }

    @Override
    public Page<City> getCities(Integer pageNum, Integer pageSize, Direction direction, String... properties) {
        PageRequest pageRequest = createPageRequest(pageNum, pageSize, direction, properties);
        return cityRepository.findAll(pageRequest);
    }

}
