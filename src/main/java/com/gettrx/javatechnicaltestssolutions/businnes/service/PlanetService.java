package com.gettrx.javatechnicaltestssolutions.businnes.service;

import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;

import java.util.List;
import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
public interface PlanetService {
    List<Planet> getAllPlanets(Integer page);
    Optional<Planet> getPlanetById(Integer planetById);
}
