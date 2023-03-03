package com.gettrx.javatechnicaltestssolutions.web.controller;

import com.gettrx.javatechnicaltestssolutions.businnes.service.PeopleService;
import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PeopleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PeopleControllerTest {

    @Mock
    private PeopleService peopleService;

    @InjectMocks
    private PeopleController peopleController = new PeopleController();

    @BeforeEach
    public void setup() {
        when(peopleService.getPeopleId(anyInt())).thenReturn(getPeople());
        when(peopleService.getAllPeople(anyInt())).thenReturn(getAllPeople());
        when(peopleService.save(any(PeopleBase.class))).thenReturn(getPeopleSave());
    }

    @Test
    void should_returnAListBiggerThanZero_when_recordsExistInSwapiApi() {
        ResponseEntity<PeopleResponse> response = peopleController.getAllPeople(1);
        int expected = 200;
        int actual = response.getStatusCode().value();
        assertEquals(expected, actual);
        assertTrue(response.getBody().getPeople().size() > 0);
    }

    @Test
    void should_returnAListEmpty_when_recordsDoNotExistInSwapiApi() {
        when(peopleService.getAllPeople(anyInt())).thenReturn(new ArrayList<>());
        ResponseEntity<PeopleResponse> response = peopleController.getAllPeople(1);
        int expected = 404;
        int actual = response.getStatusCode().value();
        assertEquals(expected, actual);
        assertTrue(response.getBody().getPeople().size() == 0);
    }

    @Test
    void should_throwAnException_when_anExceptionWasThrew() {
        when(peopleService.getAllPeople(anyInt())).thenThrow(NullPointerException.class);
        try {
            peopleController.getAllPeople(1);
        } catch (ResponseStatusException e) {
            String expected = "500 INTERNAL_SERVER_ERROR \"People Not Found\"; nested exception is java.lang.NullPointerException";
            String actual = e.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test
    void should_returnAPeople_when_recordExistsInSwapiApi() {
        ResponseEntity<PeopleBase> response = peopleController.getPeopleById(1);
        int expected = 200;
        int actual = response.getStatusCode().value();
        String expectedName = "Leia Organa";
        String actualName = response.getBody().getName();
        assertEquals(expected, actual);
        assertEquals(expectedName, actualName);
    }

    @Test
    void should_return404Status_when_recordDoesNotExistsInSwapiApi() {
        when(peopleService.getPeopleId(anyInt())).thenReturn(Optional.empty());
        try {
            peopleController.getPeopleById(1);
        } catch (ResponseStatusException e) {
            String expected = "404 NOT_FOUND \"People Not Found\"";
            String actual = e.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test
    void should_returnAPeople_when_recordExistsInSwapiApiAndSaveInsideDb() {
        ResponseEntity<People> response = peopleController.save(getPeople().get());
        int expected = 200;
        int actual = response.getStatusCode().value();
        int expectedId = 5;
        int actualId = response.getBody().getId();
        String expectedName = "Leia Organa";
        String actualName = response.getBody().getName();
        assertEquals(expected, actual);
        assertEquals(expectedName, actualName);
        assertEquals(expectedId, actualId);
    }

    @Test
    void should_throwAnException_when_recordDoesNotExist() {
        when(peopleService.save(any(PeopleBase.class))).thenReturn(Optional.empty());

        try{
            peopleController.save(getPeople().get());
        }catch(ResponseStatusException exception){
            String expected = "404 NOT_FOUND \"Error save people\"";
            String actual = exception.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test
    void should_throwAnException_when_recordDoesNotContainId() {
        People people = getPeopleSave().get();
        people.setId(0);
        when(peopleService.save(any(PeopleBase.class))).thenReturn(Optional.of(people));
        try{
            peopleController.save(getPeople().get());
        }catch(ResponseStatusException exception){
            String expected = "404 NOT_FOUND \"Error save people\"";
            String actual = exception.getMessage();
            assertEquals(expected, actual);
        }
    }

    private Optional<People> getPeopleSave() {
        People people = new People();
        people.setId(5);
        people.setName("Leia Organa");
        people.setMass("49");
        people.setCreated("2014-12-10T15:20:09.791000Z");
        people.setEdited("2014-12-20T21:17:50.315000Z");
        people.setUrl("https://swapi.dev/api/people/5/");
        people.setHeight("150");
        people.setGender("female");
        people.setSkinColor("light");
        people.setHairColor("brown");
        people.setHomeWorld("https://swapi.dev/api/planets/2/");
        return Optional.of(people);
    }

    private Optional<PeopleBase> getPeople() {
        PeopleBase people = new PeopleBase();
        people.setName("Leia Organa");
        people.setMass("49");
        people.setCreated("2014-12-10T15:20:09.791000Z");
        people.setEdited("2014-12-20T21:17:50.315000Z");
        people.setUrl("https://swapi.dev/api/people/5/");
        people.setHeight("150");
        people.setGender("female");
        people.setSkinColor("light");
        people.setHairColor("brown");
        people.setHomeWorld("https://swapi.dev/api/planets/2/");
        return Optional.ofNullable(people);
    }

    private List<PeopleBase> getAllPeople() {
        List<PeopleBase> people = new ArrayList<>();
        PeopleBase peopleBase = new PeopleBase();
        peopleBase.setName("Leia Organa");
        peopleBase.setMass("49");
        peopleBase.setCreated("2014-12-10T15:20:09.791000Z");
        peopleBase.setEdited("2014-12-20T21:17:50.315000Z");
        peopleBase.setUrl("https://swapi.dev/api/people/5/");
        peopleBase.setHeight("150");
        peopleBase.setGender("female");
        peopleBase.setSkinColor("light");
        peopleBase.setHairColor("brown");
        peopleBase.setHomeWorld("https://swapi.dev/api/planets/2/");
        people.add(peopleBase);
        return people;
    }


}