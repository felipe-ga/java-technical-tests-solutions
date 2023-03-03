package com.gettrx.javatechnicaltestssolutions.data.repository;

import com.gettrx.javatechnicaltestssolutions.data.entity.PeopleEntity;
import com.gettrx.javatechnicaltestssolutions.data.entity.PlanetEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
public interface PlanetRepository extends CrudRepository<PlanetEntity, Integer> {
    Optional<PlanetEntity> findByIdSwappi(Integer planetId);
}
