package com.gettrx.javatechnicaltestssolutions.businnes.service.impl;

import com.gettrx.javatechnicaltestssolutions.config.feign.PeopleClient;
import com.gettrx.javatechnicaltestssolutions.config.feign.PlanetClient;
import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PeopleSwapiResponse;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PlanetsSwapiResponse;
import com.gettrx.javatechnicaltestssolutions.data.entity.PeopleEntity;
import com.gettrx.javatechnicaltestssolutions.data.entity.PlanetEntity;
import com.gettrx.javatechnicaltestssolutions.data.repository.PeopleRepository;
import com.gettrx.javatechnicaltestssolutions.data.repository.PlanetRepository;
import com.google.gson.Gson;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
class PeopleServiceImplTest {

    @Mock
    private PeopleClient peopleClient;

    @Mock
    private PeopleRepository repository;

    @InjectMocks
    private PeopleServiceImpl peopleService = new PeopleServiceImpl();

    private static String json = "{ \t\"count\": 82, \t\"next\": \"https://swapi.dev/api/people/?page=2\", \t\"previous\": null, \t\"results\": [ \t\t{ \t\t\t\"name\": \"Luke Skywalker\", \t\t\t\"height\": \"172\", \t\t\t\"mass\": \"77\", \t\t\t\"hair_color\": \"blond\", \t\t\t\"skin_color\": \"fair\", \t\t\t\"eye_color\": \"blue\", \t\t\t\"birth_year\": \"19BBY\", \t\t\t\"gender\": \"male\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/1/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/2/\", \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"species\": [], \t\t\t\"vehicles\": [ \t\t\t\t\"https://swapi.dev/api/vehicles/14/\", \t\t\t\t\"https://swapi.dev/api/vehicles/30/\" \t\t\t], \t\t\t\"starships\": [ \t\t\t\t\"https://swapi.dev/api/starships/12/\", \t\t\t\t\"https://swapi.dev/api/starships/22/\" \t\t\t], \t\t\t\"created\": \"2014-12-09T13:50:51.644000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:56.891000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/1/\" \t\t}, \t\t{ \t\t\t\"name\": \"C-3PO\", \t\t\t\"height\": \"167\", \t\t\t\"mass\": \"75\", \t\t\t\"hair_color\": \"n/a\", \t\t\t\"skin_color\": \"gold\", \t\t\t\"eye_color\": \"yellow\", \t\t\t\"birth_year\": \"112BBY\", \t\t\t\"gender\": \"n/a\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/1/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/2/\", \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/4/\", \t\t\t\t\"https://swapi.dev/api/films/5/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"species\": [ \t\t\t\t\"https://swapi.dev/api/species/2/\" \t\t\t], \t\t\t\"vehicles\": [], \t\t\t\"starships\": [], \t\t\t\"created\": \"2014-12-10T15:10:51.357000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.309000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/2/\" \t\t}, \t\t{ \t\t\t\"name\": \"R2-D2\", \t\t\t\"height\": \"96\", \t\t\t\"mass\": \"32\", \t\t\t\"hair_color\": \"n/a\", \t\t\t\"skin_color\": \"white, blue\", \t\t\t\"eye_color\": \"red\", \t\t\t\"birth_year\": \"33BBY\", \t\t\t\"gender\": \"n/a\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/8/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/2/\", \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/4/\", \t\t\t\t\"https://swapi.dev/api/films/5/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"species\": [ \t\t\t\t\"https://swapi.dev/api/species/2/\" \t\t\t], \t\t\t\"vehicles\": [], \t\t\t\"starships\": [], \t\t\t\"created\": \"2014-12-10T15:11:50.376000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.311000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/3/\" \t\t}, \t\t{ \t\t\t\"name\": \"Darth Vader\", \t\t\t\"height\": \"202\", \t\t\t\"mass\": \"136\", \t\t\t\"hair_color\": \"none\", \t\t\t\"skin_color\": \"white\", \t\t\t\"eye_color\": \"yellow\", \t\t\t\"birth_year\": \"41.9BBY\", \t\t\t\"gender\": \"male\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/1/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/2/\", \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"species\": [], \t\t\t\"vehicles\": [], \t\t\t\"starships\": [ \t\t\t\t\"https://swapi.dev/api/starships/13/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T15:18:20.704000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.313000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/4/\" \t\t}, \t\t{ \t\t\t\"name\": \"Leia Organa\", \t\t\t\"height\": \"150\", \t\t\t\"mass\": \"49\", \t\t\t\"hair_color\": \"brown\", \t\t\t\"skin_color\": \"light\", \t\t\t\"eye_color\": \"brown\", \t\t\t\"birth_year\": \"19BBY\", \t\t\t\"gender\": \"female\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/2/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/2/\", \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"species\": [], \t\t\t\"vehicles\": [ \t\t\t\t\"https://swapi.dev/api/vehicles/30/\" \t\t\t], \t\t\t\"starships\": [], \t\t\t\"created\": \"2014-12-10T15:20:09.791000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.315000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/5/\" \t\t}, \t\t{ \t\t\t\"name\": \"Owen Lars\", \t\t\t\"height\": \"178\", \t\t\t\"mass\": \"120\", \t\t\t\"hair_color\": \"brown, grey\", \t\t\t\"skin_color\": \"light\", \t\t\t\"eye_color\": \"blue\", \t\t\t\"birth_year\": \"52BBY\", \t\t\t\"gender\": \"male\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/1/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/5/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"species\": [], \t\t\t\"vehicles\": [], \t\t\t\"starships\": [], \t\t\t\"created\": \"2014-12-10T15:52:14.024000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.317000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/6/\" \t\t}, \t\t{ \t\t\t\"name\": \"Beru Whitesun lars\", \t\t\t\"height\": \"165\", \t\t\t\"mass\": \"75\", \t\t\t\"hair_color\": \"brown\", \t\t\t\"skin_color\": \"light\", \t\t\t\"eye_color\": \"blue\", \t\t\t\"birth_year\": \"47BBY\", \t\t\t\"gender\": \"female\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/1/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/5/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"species\": [], \t\t\t\"vehicles\": [], \t\t\t\"starships\": [], \t\t\t\"created\": \"2014-12-10T15:53:41.121000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.319000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/7/\" \t\t}, \t\t{ \t\t\t\"name\": \"R5-D4\", \t\t\t\"height\": \"97\", \t\t\t\"mass\": \"32\", \t\t\t\"hair_color\": \"n/a\", \t\t\t\"skin_color\": \"white, red\", \t\t\t\"eye_color\": \"red\", \t\t\t\"birth_year\": \"unknown\", \t\t\t\"gender\": \"n/a\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/1/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\" \t\t\t], \t\t\t\"species\": [ \t\t\t\t\"https://swapi.dev/api/species/2/\" \t\t\t], \t\t\t\"vehicles\": [], \t\t\t\"starships\": [], \t\t\t\"created\": \"2014-12-10T15:57:50.959000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.321000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/8/\" \t\t}, \t\t{ \t\t\t\"name\": \"Biggs Darklighter\", \t\t\t\"height\": \"183\", \t\t\t\"mass\": \"84\", \t\t\t\"hair_color\": \"black\", \t\t\t\"skin_color\": \"light\", \t\t\t\"eye_color\": \"brown\", \t\t\t\"birth_year\": \"24BBY\", \t\t\t\"gender\": \"male\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/1/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\" \t\t\t], \t\t\t\"species\": [], \t\t\t\"vehicles\": [], \t\t\t\"starships\": [ \t\t\t\t\"https://swapi.dev/api/starships/12/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T15:59:50.509000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.323000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/9/\" \t\t}, \t\t{ \t\t\t\"name\": \"Obi-Wan Kenobi\", \t\t\t\"height\": \"182\", \t\t\t\"mass\": \"77\", \t\t\t\"hair_color\": \"auburn, white\", \t\t\t\"skin_color\": \"fair\", \t\t\t\"eye_color\": \"blue-gray\", \t\t\t\"birth_year\": \"57BBY\", \t\t\t\"gender\": \"male\", \t\t\t\"homeworld\": \"https://swapi.dev/api/planets/20/\", \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/2/\", \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/4/\", \t\t\t\t\"https://swapi.dev/api/films/5/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"species\": [], \t\t\t\"vehicles\": [ \t\t\t\t\"https://swapi.dev/api/vehicles/38/\" \t\t\t], \t\t\t\"starships\": [ \t\t\t\t\"https://swapi.dev/api/starships/48/\", \t\t\t\t\"https://swapi.dev/api/starships/59/\", \t\t\t\t\"https://swapi.dev/api/starships/64/\", \t\t\t\t\"https://swapi.dev/api/starships/65/\", \t\t\t\t\"https://swapi.dev/api/starships/74/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T16:16:29.192000Z\", \t\t\t\"edited\": \"2014-12-20T21:17:50.325000Z\", \t\t\t\"url\": \"https://swapi.dev/api/people/10/\" \t\t} \t] }";

    @BeforeEach
    public void setup() {
        when(peopleClient.getAllPeople(anyInt())).thenReturn(getPeopleSwapiResponse());
        when(repository.findById(anyInt())).thenReturn(getPeopleEntity());
        when(peopleClient.getPeopleById(anyInt())).thenReturn(getPeopleBase());
        when(repository.save(any())).thenReturn(getPeopleEntity().get());
    }

    @Test
    void should_returnAListBiggerThanZero_when_recordsExistInSwapiApi() {
        List<PeopleBase> response = peopleService.getAllPeople(1);
        int expected = 10;
        int actual = response.size();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAEmptyList_when_recordsDoNotExistInSwapiApi() {
        when(peopleClient.getAllPeople(anyInt())).thenReturn(null);
        List<PeopleBase> response = peopleService.getAllPeople(1);
        int expected = 0;
        int actual = response.size();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAEmptyList_when_wapiApiReturnEmpty() {
        when(peopleClient.getAllPeople(anyInt())).thenReturn(Optional.empty());
        List<PeopleBase> response = peopleService.getAllPeople(1);
        int expected = 0;
        int actual = response.size();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAEmptyList_when_swapiThrowAnException() {
        when(peopleClient.getAllPeople(anyInt())).thenThrow(NullPointerException.class);
        List<PeopleBase> response = peopleService.getAllPeople(1);
        int expected = 0;
        int actual = response.size();
        assertEquals(expected, actual);
    }


    @Test
    void should_returnAPeople_when_saveInsideDbSuccessfully() {
        Optional<People> response = peopleService.save(new PeopleBase());
        String expected = "Luke Skywalker";
        String actual = response.get().getName();
        assertEquals(expected, actual);
    }


    @Test
    void should_returnAPeopleFromDb_when_recordExistInsideDb() {
        Optional<PeopleBase> response = peopleService.getPeopleId(1);
        String expected = "Luke Skywalker";
        String actual = response.get().getName();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAPeopleFromSwapiApiAndSaveInsideDb_when_idPeopleDoesNotExist() {
        Optional<PeopleEntity> peopleEntity = getPeopleEntity();
        peopleEntity.get().setId(0);
        when(repository.findById(anyInt())).thenReturn(peopleEntity);
        Optional<PeopleBase> response = peopleService.getPeopleId(1);
        String expected = "Luke Skywalker";
        String actual = response.get().getName();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAPeopleFromSwapiApiAndSaveInsideDb_when_recordDoesNotExist() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        Optional<PeopleBase> response = peopleService.getPeopleId(1);
        String expected = "Luke Skywalker";
        String actual = response.get().getName();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnEmptyPeople_when_recordDoesNotExistInSwapiApi() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        when(peopleClient.getPeopleById(anyInt())).thenReturn(Optional.empty());
        Optional<PeopleBase> response = peopleService.getPeopleId(1);
        assertFalse(response.isPresent());
    }

    @Test
    void should_returnEmptyPeople_when_someErrorHappen() {
        when(repository.findById(anyInt())).thenThrow(NullPointerException.class);
        Optional<PeopleBase> response = peopleService.getPeopleId(1);
        assertFalse(response.isPresent());
    }


    @Test
    void should_throwAnException_when_someExceptionHappen() {
        when(repository.save(any())).thenThrow(NullPointerException.class);
        Optional<People> response = peopleService.save(new PeopleBase());
        assertFalse(response.isPresent());
    }

    private Optional<PeopleBase> getPeopleBase(){
        PeopleBase peopleBase = new PeopleBase();
        peopleBase.setName("Luke Skywalker");
        peopleBase.setBirthYear("19BBY");
        peopleBase.setEyeColor("blue");
        peopleBase.setGender("male");
        peopleBase.setHairColor("blond");
        peopleBase.setHeight("172");
        peopleBase.setHomeWorld("https://swapi.dev/api/planets/1/");
        peopleBase.setMass("77");
        peopleBase.setSkinColor("fair");
        peopleBase.setCreated("2014-12-09T13:50:51.644000Z");
        peopleBase.setEdited("2014-12-20T21:17:56.891000Z");
        peopleBase.setUrl("https://swapi.dev/api/people/1/");
        return Optional.of(peopleBase);
    }
    private Optional<PeopleEntity> getPeopleEntity() {
        PeopleEntity peopleEntity = PeopleEntity
                .builder()
                .id(1)
                .name("Luke Skywalker")
                .birthYear("19BBY")
                .eyeColor("blue")
                .gender("male")
                .hairColor("blond")
                .height("172")
                .homeWorld("https://swapi.dev/api/planets/1/")
                .mass("77")
                .skinColor("fair")
                .created("2014-12-09T13:50:51.644000Z")
                .edited("2014-12-20T21:17:56.891000Z")
                .url("https://swapi.dev/api/people/1/")
                .build();
        return Optional.of(peopleEntity);
    }

    private Optional<PeopleSwapiResponse> getPeopleSwapiResponse() {
        Gson gson = new Gson();
        PeopleSwapiResponse response = gson.fromJson(json, PeopleSwapiResponse.class);
        return Optional.of(response);
    }

}