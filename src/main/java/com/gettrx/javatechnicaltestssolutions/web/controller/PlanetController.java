package com.gettrx.javatechnicaltestssolutions.web.controller;


import com.gettrx.javatechnicaltestssolutions.businnes.service.PlanetService;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PlanetsResponse;
import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;
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
public class PlanetController {
    @Autowired
    private PlanetService planetService;

    @GetMapping("/")
    public ResponseEntity<PlanetsResponse> getAllPlanets(@Valid @RequestParam(value = "page") Integer page) {
        try {
            PlanetsResponse planetsResponse = new PlanetsResponse();
            planetsResponse.setPlanets(planetService.getAllPlanets(page));
            HttpStatus status = HttpStatus.OK;
            if (planetsResponse.getPlanets().isEmpty()) {
                status = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<>(planetsResponse, status);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Planets Not Found", exception);
        }

    }

    @GetMapping("/{planetId}")
    public ResponseEntity<Planet> getPlanetById(@Valid @PathVariable Integer planetId) {
        Optional<Planet> planet = planetService.getPlanetById(planetId);
        if(!planet.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Planet Not Found");
        }
        return new ResponseEntity<>(planet.get(), HttpStatus.OK);
    }
}
