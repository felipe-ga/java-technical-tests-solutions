package com.gettrx.javatechnicaltestssolutions.web.controller;


import com.gettrx.javatechnicaltestssolutions.constant.PeopleControllerConstant;
import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PeopleResponse;
import com.gettrx.javatechnicaltestssolutions.businnes.service.PeopleService;
import com.gettrx.javatechnicaltestssolutions.data.entity.PeopleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@RestController
@RequestMapping("${api.prefix}${api.version}people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/")
    public ResponseEntity<PeopleResponse> getAllPeople(@Valid @RequestParam(value = "page") Integer page) {
        try {
            PeopleResponse peopleResponse = new PeopleResponse();
            peopleResponse.setPeople(peopleService.getAllPeople(page));
            HttpStatus status = HttpStatus.OK;
            if (peopleResponse.getPeople().isEmpty()) {
                status = HttpStatus.NOT_FOUND;
            }
            return new ResponseEntity<>(peopleResponse, status);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, PeopleControllerConstant.ERROR_NOT_FOUND_MESSAGE, exception);
        }

    }

    @GetMapping("/{peopleId}")
    public ResponseEntity<PeopleBase> getPeopleById(@Valid @PathVariable Integer peopleId) {
        Optional<PeopleBase> people = peopleService.getPeopleId(peopleId);
        if (!people.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, PeopleControllerConstant.ERROR_NOT_FOUND_MESSAGE);
        }
        return new ResponseEntity<>(people.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<People> save(@Valid @RequestBody PeopleBase peopleBase) {
        Optional<People> people = peopleService.save(peopleBase);
        if (people.isPresent() && people.get().getId() > 0) {
            return new ResponseEntity<>(people.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, PeopleControllerConstant.ERROR_SAVE_MESSAGE);
    }
}
