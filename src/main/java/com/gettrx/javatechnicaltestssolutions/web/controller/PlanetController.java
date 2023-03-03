package com.gettrx.javatechnicaltestssolutions.web.controller;


import com.gettrx.javatechnicaltestssolutions.businnes.service.PlanetService;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PlanetsResponse;
import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@RestController
@RequestMapping("${api.prefix}${api.version}planets")
@Slf4j
public class PlanetController {
    @Autowired
    private PlanetService planetService;

    @GetMapping("/")
    @ApiOperation(value = "get all planets",
            notes = "return list of planets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "planets found"),
            @ApiResponse(code = 404, message = "planets not found"),
            @ApiResponse(code = 500, message = "internal server error")})
    public ResponseEntity<PlanetsResponse> getAllPlanets(@Valid @RequestParam(value = "page") Integer page) {
        try {
            PlanetsResponse planetsResponse = new PlanetsResponse();
            planetsResponse.setPlanets(planetService.getAllPlanets(page));
            HttpStatus status = HttpStatus.OK;
            if (planetsResponse.getPlanets().isEmpty()) {
                log.error("planets not found");
                status = HttpStatus.NOT_FOUND;
            }
            log.error("planets found");
            return new ResponseEntity<>(planetsResponse, status);
        } catch (Exception exception) {
            log.error("planets not found");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Planets Not Found", exception);
        }

    }

    @GetMapping("/{planetId}")
    @ApiOperation(value = "get planet by id",
            notes = "return a planet object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "planet found"),
            @ApiResponse(code = 404, message = "planet not found"),
            @ApiResponse(code = 500, message = "internal server error")})
    public ResponseEntity<Planet> getPlanetById(@Valid @PathVariable Integer planetId) {
        Optional<Planet> planet = planetService.getPlanetById(planetId);
        if(!planet.isPresent()){
            log.debug("planet not found {}", planetId);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Planet Not Found");
        }
        log.debug("planet found {}", planetId);
        return new ResponseEntity<>(planet.get(), HttpStatus.OK);
    }
}
