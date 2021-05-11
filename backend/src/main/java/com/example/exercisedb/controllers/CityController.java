package com.example.exercisedb.controllers;

import com.example.exercisedb.dto.responses.BaseErrorResponse;
import com.example.exercisedb.dto.responses.CityResponse;
import com.example.exercisedb.dto.responses.BadRequestResponse;
import com.example.exercisedb.dto.responses.LISResponse;
import com.example.exercisedb.services.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/cities")
@Data
@Validated
public class CityController {

    private final CityService cityService;

    @ApiOperation(value = "", notes = "Returns the list of the cities ids Longest Increasing Subsequence")
    @ApiResponses({
        @ApiResponse(responseCode  = "200", description = "Success",  content = @Content(schema = @Schema(implementation = LISResponse.class))),
        @ApiResponse(responseCode  = "500", description = "Internal Server Error",  content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @GetMapping("/ids/lis")
    public LISResponse getCitiesIdsLIS() {
        return new LISResponse(cityService.getIdsLIS());
    }

    @ApiOperation(value = "", notes = "Returns a paginated list of cities")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description = "Success",  content = @Content(schema = @Schema(implementation = LISResponse.class))),
            @ApiResponse(responseCode  = "400", description = "Bad Request",  content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode  = "500", description = "Internal Server Error",  content = @Content(schema = @Schema(implementation = BaseErrorResponse.class)))
    })
    @CrossOrigin
    @GetMapping("/queryByPage")
    public CityResponse getCities(@RequestParam @Min(0) Integer page, @RequestParam @Min(1) Integer size) {
        return new CityResponse(cityService.getCities(page, size));
    }

}
