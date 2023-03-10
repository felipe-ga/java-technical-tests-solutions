package com.gettrx.javatechnicaltestssolutions.data.repository;

import com.gettrx.javatechnicaltestssolutions.data.entity.PeopleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
public interface PeopleRepository extends CrudRepository<PeopleEntity, Integer> {
    Optional<PeopleEntity> findByIdSwappi(Integer peopleId);
}
