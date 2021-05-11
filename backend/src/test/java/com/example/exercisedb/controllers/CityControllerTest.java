package com.example.exercisedb.controllers;

import com.example.exercisedb.dto.responses.CityResponse;
import com.example.exercisedb.dto.responses.LISResponse;
import com.example.exercisedb.entities.City;
import com.example.exercisedb.services.CityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityControllerTest {

    @Mock
    private CityServiceImpl cityService;

    @InjectMocks
    private CityController cityController;

    @Test
    void getCitiesIdsLIS() {
        Long[] longLIS = new Long[] {67L, 157L, 262L, 297L};
        LISResponse lisResponse = new LISResponse(asList(longLIS));
        when(cityService.getIdsLIS()).thenReturn(asList(longLIS));
        assertEquals(lisResponse, cityController.getCitiesIdsLIS());
    }

    @Test
    void getCities() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City());
        Page<City> cityPage = new PageImpl<>(cityList);
        CityResponse cityResponse = new CityResponse(cityPage);
        when(cityService.getCities(0, 1)).thenReturn(cityPage);
        assertEquals(cityResponse, cityController.getCities(0, 1));
    }
}