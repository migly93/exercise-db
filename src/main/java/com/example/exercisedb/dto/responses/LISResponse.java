package com.example.exercisedb.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LISResponse {

    private List<Long> lis;
}
