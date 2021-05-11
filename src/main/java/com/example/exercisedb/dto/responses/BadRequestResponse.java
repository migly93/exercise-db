package com.example.exercisedb.dto.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class BadRequestResponse extends BaseErrorResponse {

    private Map<String, String> errors;

    public BadRequestResponse(Map<String, String> errors, int status, String message) {
        super(status, message);
        this.errors = errors;
    }
}
