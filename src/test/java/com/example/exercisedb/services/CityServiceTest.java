package com.example.exercisedb.services;

import com.example.exercisedb.entities.City;
import com.example.exercisedb.repositories.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    private final Long[] longArray = new Long[] {233L, 67L, 157L, 262L, 255L, 75L, 297L, 124L, 197L, 43L};
    private Page<City> cityPage;

    private void createOutputPage() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City());
        cityPage = new PageImpl<>(cityList);
    }

    @Test
    void getCitiesIds() {
        when(cityRepository.findAllIds(any(PageRequest.class))).thenReturn(asList(longArray));
        assertEquals(asList(longArray), cityService.getCitiesIds(null, null, null, (String[]) null));
    }

    @Test
    void getIdsLIS() {
        Long[] longLIS = new Long[] {67L, 157L, 262L, 297L};
        when(cityRepository.findAllIds(any(PageRequest.class))).thenReturn(asList(longArray));
        assertEquals(asList(longLIS), cityService.getIdsLIS());
        assertEquals(asList(longLIS), cityService.getIdsLIS(null, null, null, (String[]) null));
    }

    @Test
    void getCitiesOnlyPaged() {
        createOutputPage();
        when(cityRepository.findAll(any(PageRequest.class))).thenReturn(cityPage);
        assertEquals(cityPage, cityService.getCities(null, null));
    }

    @Test
    void getCitiesPagedSorted() {
        createOutputPage();
        when(cityRepository.findAll(any(PageRequest.class))).thenReturn(cityPage);
        assertEquals(cityPage, cityService.getCities(null, null, null, (String[]) null));
    }
}