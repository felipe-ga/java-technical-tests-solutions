package com.gettrx.javatechnicaltestssolutions.businnes.service.impl;

import com.gettrx.javatechnicaltestssolutions.businnes.service.PeopleService;
import com.gettrx.javatechnicaltestssolutions.config.feign.PeopleClient;
import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PeopleSwapiResponse;
import com.gettrx.javatechnicaltestssolutions.data.entity.PeopleEntity;
import com.gettrx.javatechnicaltestssolutions.data.repository.PeopleRepository;
import com.gettrx.javatechnicaltestssolutions.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Service
@Slf4j
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleClient peopleClient;

    @Autowired
    private PeopleRepository repository;

    @Override
    public List<PeopleBase> getAllPeople(Integer page) {
        List<PeopleBase> people = new ArrayList<>();
        IntStream.range(1, page + 1).forEach(index -> {
            try {
                PeopleSwapiResponse response = peopleClient.getAllPeople(index)
                        .orElse(new PeopleSwapiResponse());
                if (!response.getResults().isEmpty()) {
                    people.addAll(response.getResults());
                }
            } catch (Exception exception) {
                log.error("Exception {}", exception.getMessage());
            }
        });
        return people;
    }

    @Override
    public Optional<PeopleBase> getPeopleId(Integer peopleId) {
        try {
            Optional<PeopleEntity> peopleEntity = repository.findById(peopleId);
            if (peopleEntity.isPresent() && peopleEntity.get().getId() > 0) {
                log.debug("people found inside db {}", peopleId);
                return Optional.ofNullable(PeopleMapper.peopleEntityToPeople(peopleEntity.get()));
            }
            Optional<PeopleBase> people = peopleClient.getPeopleById(peopleId);
            if(people.isPresent()){
                log.debug("saving people inside db {}", peopleId);
                repository.save(PeopleMapper.peopleToPeopleEntity(people.get(), peopleId));
            }
            return people;
        } catch (Exception exception) {
            log.error("Exception {}", exception.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<People> save(PeopleBase people) {
        try {
            PeopleEntity peopleEntity = repository.save(PeopleMapper.peopleToPeopleEntity(people, null));
            return Optional.ofNullable(PeopleMapper.peopleEntityToPeople(peopleEntity));
        } catch (Exception exception) {
            log.error("Exception {}", exception.getMessage());
            return Optional.empty();
        }
    }
}
