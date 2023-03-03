package com.gettrx.javatechnicaltestssolutions.businnes.service.impl;

import com.gettrx.javatechnicaltestssolutions.businnes.service.PlanetService;
import com.gettrx.javatechnicaltestssolutions.config.feign.PlanetClient;
import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PlanetSwapi;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PlanetsSwapiResponse;
import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;
import com.gettrx.javatechnicaltestssolutions.data.entity.PeopleEntity;
import com.gettrx.javatechnicaltestssolutions.data.entity.PlanetEntity;
import com.gettrx.javatechnicaltestssolutions.data.repository.PlanetRepository;
import com.gettrx.javatechnicaltestssolutions.mapper.PeopleMapper;
import com.gettrx.javatechnicaltestssolutions.mapper.PlanetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Service
@Slf4j
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetClient planetClient;

    @Autowired
    private PlanetRepository repository;

    @Override
    public List<Planet> getAllPlanets(Integer page) {
        List<Planet> planets = new ArrayList<>();
        IntStream.range(1, page + 1).forEach(index -> {
            try {
                PlanetsSwapiResponse response = planetClient.getAllPlanets(index)
                        .orElse(new PlanetsSwapiResponse());
                if (!response.getResults().isEmpty()) {
                    planets.addAll(response.getResults().stream()
                            .map(planet -> PlanetMapper.PlanetSwapiToPlanetDto(planet))
                            .collect(Collectors.toList()));
                }
            } catch (Exception exception) {
                log.error("Exception {}", exception.getMessage());
            }
        });
        return planets;
    }

    @Override
    public Optional<Planet> getPlanetById(Integer planetId) {
        try {
            Optional<PlanetEntity> planetEntity = repository.findById(planetId);
            if (planetEntity.isPresent() && planetEntity.get().getId() > 0) {
                log.info("planet found inside db {}", planetId);
                return Optional.ofNullable(PlanetMapper.PlanetEntityToPlanet(planetEntity.get()));
            }
            Optional<PlanetSwapi> planet = planetClient.getPlanetsById(planetId);
            if (planet.isPresent()) {
                log.info("saving planet inside db {}", planetId);
                repository.save(PlanetMapper.PlanetSwapiToPlanetEntity(planet.get(), planetId));
                return Optional.ofNullable(PlanetMapper.PlanetSwapiToPlanetDto(planet.get()));
            }else{
                return Optional.empty();
            }

        } catch (Exception exception) {
            log.error("Exception {}", exception.getMessage());
            return Optional.empty();
        }
    }
}
