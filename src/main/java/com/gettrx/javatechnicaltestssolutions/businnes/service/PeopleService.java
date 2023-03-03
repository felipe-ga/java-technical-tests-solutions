package com.gettrx.javatechnicaltestssolutions.businnes.service;

import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;

import java.util.List;
import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
public interface PeopleService {
    List<PeopleBase> getAllPeople(Integer page);

    Optional<PeopleBase> getPeopleId(Integer peopleId);

    Optional<People> save(PeopleBase people);
}
