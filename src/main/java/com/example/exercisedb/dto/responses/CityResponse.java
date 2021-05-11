package com.example.exercisedb.dto.responses;

import com.example.exercisedb.entities.City;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CityResponse {

    private List<City> content;
    private int totalPages;
    private long totalElements;

    public CityResponse(Page<City> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }

}
