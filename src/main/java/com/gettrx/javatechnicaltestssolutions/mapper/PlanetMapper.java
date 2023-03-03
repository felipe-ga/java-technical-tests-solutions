package com.gettrx.javatechnicaltestssolutions.mapper;

import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;
import com.gettrx.javatechnicaltestssolutions.data.dto.PlanetSwapi;
import com.gettrx.javatechnicaltestssolutions.data.entity.PlanetEntity;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
public class PlanetMapper {
    public static Planet PlanetSwapiToPlanetDto(PlanetSwapi planetSwapi) {
        Planet planet = new Planet();
        planet.setClimate(planetSwapi.getClimate());
        planet.setDiameter(planetSwapi.getDiameter());
        planet.setGravity(planetSwapi.getGravity());
        planet.setName(planetSwapi.getName());
        planet.setPopulation(planetSwapi.getPopulation());
        planet.setTerrain(planetSwapi.getTerrain());
        planet.setUrl(planetSwapi.getUrl());
        planet.setResidents(Optional.ofNullable(planetSwapi.getResidents()).orElse(new ArrayList<>()).toString());
        return planet;

    }

    public static PlanetEntity PlanetSwapiToPlanetEntity(PlanetSwapi planetSwapi, Integer planetId) {
        PlanetEntity planetEntity = new PlanetEntity();
        planetEntity.setId(planetId);
        planetEntity.setClimate(planetSwapi.getClimate());
        planetEntity.setClimate(planetSwapi.getClimate());
        planetEntity.setDiameter(planetSwapi.getDiameter());
        planetEntity.setGravity(planetSwapi.getGravity());
        planetEntity.setName(planetSwapi.getName());
        planetEntity.setPopulation(planetSwapi.getPopulation());
        planetEntity.setTerrain(planetSwapi.getTerrain());
        planetEntity.setUrl(planetSwapi.getUrl());
        planetEntity.setResidents(Optional.ofNullable(planetSwapi.getResidents())
                .orElse(new ArrayList<>()).toString());
        return planetEntity;
    }

    public static Planet PlanetEntityToPlanet(PlanetEntity planetEntity) {
        Planet planet = new Planet();
        planet.setClimate(planetEntity.getClimate());
        planet.setDiameter(planetEntity.getDiameter());
        planet.setGravity(planetEntity.getGravity());
        planet.setName(planetEntity.getName());
        planet.setPopulation(planetEntity.getPopulation());
        planet.setTerrain(planetEntity.getTerrain());
        planet.setUrl(planetEntity.getUrl());
        planet.setResidents(planetEntity.getResidents());
        return  planet;
    }
}
