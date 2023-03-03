package com.gettrx.javatechnicaltestssolutions.web.controller;

import com.gettrx.javatechnicaltestssolutions.businnes.service.PeopleService;
import com.gettrx.javatechnicaltestssolutions.businnes.service.PlanetService;
import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PeopleResponse;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PlanetsResponse;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlanetControllerTest {
    @Mock
    private PlanetService planetService;

    @InjectMocks
    private PlanetController planetController = new PlanetController();

    @BeforeEach
    public void setup() {
        when(planetService.getPlanetById(anyInt())).thenReturn(getPlanet());
        when(planetService.getAllPlanets(anyInt())).thenReturn(getPlanets());
    }

    @Test
    void should_returnAListBiggerThanZero_when_recordsExistInSwapiApi(){
        ResponseEntity<PlanetsResponse> response = planetController.getAllPlanets(1);
        int expected = 200;
        int actual = response.getStatusCode().value();
        assertEquals(expected, actual);
        assertTrue(response.getBody().getPlanets().size() > 0);
    }

    @Test
    void should_returnAListEmpty_when_recordsDoNotExistInSwapiApi(){
        when(planetService.getAllPlanets(anyInt())).thenReturn(new ArrayList<>());
        ResponseEntity<PlanetsResponse> response = planetController.getAllPlanets(1);
        int expected = 404;
        int actual = response.getStatusCode().value();
        assertEquals(expected, actual);
        assertTrue(response.getBody().getPlanets().size() == 0);
    }

    @Test
    void should_throwAnException_when_anExceptionWasThrew(){
        when(planetService.getAllPlanets(anyInt())).thenThrow(NullPointerException.class);
        try{
            planetController.getAllPlanets(1);
        }catch(ResponseStatusException e){
            String expected = "500 INTERNAL_SERVER_ERROR \"Planets Not Found\"; nested exception is java.lang.NullPointerException";
            String actual = e.getMessage();
            assertEquals(expected, actual);
        }
    }

    @Test
    void should_returnAPlanet_when_recordExistsInSwapiApi(){
        ResponseEntity<Planet> response = planetController.getPlanetById(1);
        int expected = 200;
        int actual = response.getStatusCode().value();
        String expectedName = "Geonosis";
        String actualName = response.getBody().getName();
        assertEquals(expected, actual);
        assertEquals(expectedName, actualName);
    }

    @Test
    void should_return404Status_when_recordDoesNotExistsInSwapiApi(){
        when(planetService.getPlanetById(anyInt())).thenReturn(Optional.empty());
        try{
            planetController.getPlanetById(1);
        }catch(ResponseStatusException e){
            String expected = "404 NOT_FOUND \"Planet Not Found\"";
            String actual = e.getMessage();
            assertEquals(expected, actual);
        }
    }

    private List<Planet> getPlanets(){
        List<Planet> planets = new ArrayList<>();
        planets.add(getPlanet().get());
        Planet planet = new Planet();
        planet.setClimate("arid");
        planet.setDiameter("10465");
        planet.setGravity("1 standard");
        planet.setName("Tatooine");
        planet.setPopulation("200000");
        planet.setTerrain("desert");
        planet.setUrl("https://swapi.dev/api/planets/1/");
        planet.setResidents("https://swapi.dev/api/people/1/, https://swapi.dev/api/people/2/");
        planets.add(planet);
        return planets;
    }

    private Optional<Planet> getPlanet(){
        Planet planet = new Planet();
        planet.setClimate("temperate, arid");
        planet.setDiameter("11370");
        planet.setGravity("0.9 standard");
        planet.setName("Geonosis");
        planet.setPopulation("100000000000");
        planet.setTerrain("rock, desert, mountain, barren");
        planet.setUrl("https://swapi.dev/api/planets/11/");
        planet.setResidents("https://swapi.dev/api/people/63/");
        return Optional.ofNullable(planet);
    }


}