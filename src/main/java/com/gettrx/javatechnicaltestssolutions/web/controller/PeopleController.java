package com.gettrx.javatechnicaltestssolutions.web.controller;


import com.gettrx.javatechnicaltestssolutions.businnes.service.PeopleService;
import com.gettrx.javatechnicaltestssolutions.constant.PeopleControllerConstant;
import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PeopleResponse;
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
@RequestMapping("${api.prefix}${api.version}people")
@Slf4j
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("/")
    @ApiOperation(value = "Find all people",
            notes = "return a list of people")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "people found"),
            @ApiResponse(code = 404, message = "people not found"),
            @ApiResponse(code = 500, message = "internal server error")})
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
            log.error("Exception {}", exception.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, PeopleControllerConstant.ERROR_NOT_FOUND_MESSAGE, exception);
        }

    }

    @GetMapping("/{peopleId}")
    @ApiOperation(value = "Find people by id",
            notes = "return a people object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "people found"),
            @ApiResponse(code = 404, message = "people not found"),
            @ApiResponse(code = 500, message = "internal server error")})
    public ResponseEntity<PeopleBase> getPeopleById(@Valid @PathVariable Integer peopleId) {
        Optional<PeopleBase> people = peopleService.getPeopleId(peopleId);
        if (!people.isPresent()) {
            log.debug("people not found {}", peopleId);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, PeopleControllerConstant.ERROR_NOT_FOUND_MESSAGE);
        }
        return new ResponseEntity<>(people.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "save people",
            notes = "return a people object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "people save successfully"),
            @ApiResponse(code = 500, message = "internal server error")})
    public ResponseEntity<People> save(@Valid @RequestBody PeopleBase peopleBase) {
        Optional<People> people = peopleService.save(peopleBase);
        if (people.isPresent() && people.get().getId() > 0) {
            log.debug("people was save successfully {}", peopleBase.toString());
            return new ResponseEntity<>(people.get(), HttpStatus.OK);
        }
        log.debug("people was not save successfully {}", peopleBase.toString());
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, PeopleControllerConstant.ERROR_SAVE_MESSAGE);
    }
}
