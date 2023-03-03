package com.gettrx.javatechnicaltestssolutions.businnes.service.impl;

import com.gettrx.javatechnicaltestssolutions.config.feign.PlanetClient;
import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;
import com.gettrx.javatechnicaltestssolutions.data.dto.PlanetSwapi;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PlanetsSwapiResponse;
import com.gettrx.javatechnicaltestssolutions.data.entity.PlanetEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


/**
 * @author galindoaguilarf
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlanetServiceImplTest {

    @Mock
    private PlanetClient planetClient;

    @Mock
    private PlanetRepository repository;

    @InjectMocks
    private PlanetServiceImpl planetService = new PlanetServiceImpl();

    private static String json = "{ \t\"count\": 60, \t\"next\": \"https://swapi.dev/api/planets/?page=2\", \t\"previous\": null, \t\"results\": [ \t\t{ \t\t\t\"name\": \"Tatooine\", \t\t\t\"rotation_period\": \"23\", \t\t\t\"orbital_period\": \"304\", \t\t\t\"diameter\": \"10465\", \t\t\t\"climate\": \"arid\", \t\t\t\"gravity\": \"1 standard\", \t\t\t\"terrain\": \"desert\", \t\t\t\"surface_water\": \"1\", \t\t\t\"population\": \"200000\", \t\t\t\"residents\": [ \t\t\t\t\"https://swapi.dev/api/people/1/\", \t\t\t\t\"https://swapi.dev/api/people/2/\", \t\t\t\t\"https://swapi.dev/api/people/4/\", \t\t\t\t\"https://swapi.dev/api/people/6/\", \t\t\t\t\"https://swapi.dev/api/people/7/\", \t\t\t\t\"https://swapi.dev/api/people/8/\", \t\t\t\t\"https://swapi.dev/api/people/9/\", \t\t\t\t\"https://swapi.dev/api/people/11/\", \t\t\t\t\"https://swapi.dev/api/people/43/\", \t\t\t\t\"https://swapi.dev/api/people/62/\" \t\t\t], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/4/\", \t\t\t\t\"https://swapi.dev/api/films/5/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"created\": \"2014-12-09T13:50:49.641000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.411000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/1/\" \t\t}, \t\t{ \t\t\t\"name\": \"Alderaan\", \t\t\t\"rotation_period\": \"24\", \t\t\t\"orbital_period\": \"364\", \t\t\t\"diameter\": \"12500\", \t\t\t\"climate\": \"temperate\", \t\t\t\"gravity\": \"1 standard\", \t\t\t\"terrain\": \"grasslands, mountains\", \t\t\t\"surface_water\": \"40\", \t\t\t\"population\": \"2000000000\", \t\t\t\"residents\": [ \t\t\t\t\"https://swapi.dev/api/people/5/\", \t\t\t\t\"https://swapi.dev/api/people/68/\", \t\t\t\t\"https://swapi.dev/api/people/81/\" \t\t\t], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T11:35:48.479000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.420000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/2/\" \t\t}, \t\t{ \t\t\t\"name\": \"Yavin IV\", \t\t\t\"rotation_period\": \"24\", \t\t\t\"orbital_period\": \"4818\", \t\t\t\"diameter\": \"10200\", \t\t\t\"climate\": \"temperate, tropical\", \t\t\t\"gravity\": \"1 standard\", \t\t\t\"terrain\": \"jungle, rainforests\", \t\t\t\"surface_water\": \"8\", \t\t\t\"population\": \"1000\", \t\t\t\"residents\": [], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/1/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T11:37:19.144000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.421000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/3/\" \t\t}, \t\t{ \t\t\t\"name\": \"Hoth\", \t\t\t\"rotation_period\": \"23\", \t\t\t\"orbital_period\": \"549\", \t\t\t\"diameter\": \"7200\", \t\t\t\"climate\": \"frozen\", \t\t\t\"gravity\": \"1.1 standard\", \t\t\t\"terrain\": \"tundra, ice caves, mountain ranges\", \t\t\t\"surface_water\": \"100\", \t\t\t\"population\": \"unknown\", \t\t\t\"residents\": [], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/2/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T11:39:13.934000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.423000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/4/\" \t\t}, \t\t{ \t\t\t\"name\": \"Dagobah\", \t\t\t\"rotation_period\": \"23\", \t\t\t\"orbital_period\": \"341\", \t\t\t\"diameter\": \"8900\", \t\t\t\"climate\": \"murky\", \t\t\t\"gravity\": \"N/A\", \t\t\t\"terrain\": \"swamp, jungles\", \t\t\t\"surface_water\": \"8\", \t\t\t\"population\": \"unknown\", \t\t\t\"residents\": [], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/2/\", \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T11:42:22.590000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.425000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/5/\" \t\t}, \t\t{ \t\t\t\"name\": \"Bespin\", \t\t\t\"rotation_period\": \"12\", \t\t\t\"orbital_period\": \"5110\", \t\t\t\"diameter\": \"118000\", \t\t\t\"climate\": \"temperate\", \t\t\t\"gravity\": \"1.5 (surface), 1 standard (Cloud City)\", \t\t\t\"terrain\": \"gas giant\", \t\t\t\"surface_water\": \"0\", \t\t\t\"population\": \"6000000\", \t\t\t\"residents\": [ \t\t\t\t\"https://swapi.dev/api/people/26/\" \t\t\t], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/2/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T11:43:55.240000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.427000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/6/\" \t\t}, \t\t{ \t\t\t\"name\": \"Endor\", \t\t\t\"rotation_period\": \"18\", \t\t\t\"orbital_period\": \"402\", \t\t\t\"diameter\": \"4900\", \t\t\t\"climate\": \"temperate\", \t\t\t\"gravity\": \"0.85 standard\", \t\t\t\"terrain\": \"forests, mountains, lakes\", \t\t\t\"surface_water\": \"8\", \t\t\t\"population\": \"30000000\", \t\t\t\"residents\": [ \t\t\t\t\"https://swapi.dev/api/people/30/\" \t\t\t], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/3/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T11:50:29.349000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.429000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/7/\" \t\t}, \t\t{ \t\t\t\"name\": \"Naboo\", \t\t\t\"rotation_period\": \"26\", \t\t\t\"orbital_period\": \"312\", \t\t\t\"diameter\": \"12120\", \t\t\t\"climate\": \"temperate\", \t\t\t\"gravity\": \"1 standard\", \t\t\t\"terrain\": \"grassy hills, swamps, forests, mountains\", \t\t\t\"surface_water\": \"12\", \t\t\t\"population\": \"4500000000\", \t\t\t\"residents\": [ \t\t\t\t\"https://swapi.dev/api/people/3/\", \t\t\t\t\"https://swapi.dev/api/people/21/\", \t\t\t\t\"https://swapi.dev/api/people/35/\", \t\t\t\t\"https://swapi.dev/api/people/36/\", \t\t\t\t\"https://swapi.dev/api/people/37/\", \t\t\t\t\"https://swapi.dev/api/people/38/\", \t\t\t\t\"https://swapi.dev/api/people/39/\", \t\t\t\t\"https://swapi.dev/api/people/42/\", \t\t\t\t\"https://swapi.dev/api/people/60/\", \t\t\t\t\"https://swapi.dev/api/people/61/\", \t\t\t\t\"https://swapi.dev/api/people/66/\" \t\t\t], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/4/\", \t\t\t\t\"https://swapi.dev/api/films/5/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T11:52:31.066000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.430000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/8/\" \t\t}, \t\t{ \t\t\t\"name\": \"Coruscant\", \t\t\t\"rotation_period\": \"24\", \t\t\t\"orbital_period\": \"368\", \t\t\t\"diameter\": \"12240\", \t\t\t\"climate\": \"temperate\", \t\t\t\"gravity\": \"1 standard\", \t\t\t\"terrain\": \"cityscape, mountains\", \t\t\t\"surface_water\": \"unknown\", \t\t\t\"population\": \"1000000000000\", \t\t\t\"residents\": [ \t\t\t\t\"https://swapi.dev/api/people/34/\", \t\t\t\t\"https://swapi.dev/api/people/55/\", \t\t\t\t\"https://swapi.dev/api/people/74/\" \t\t\t], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/3/\", \t\t\t\t\"https://swapi.dev/api/films/4/\", \t\t\t\t\"https://swapi.dev/api/films/5/\", \t\t\t\t\"https://swapi.dev/api/films/6/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T11:54:13.921000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.432000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/9/\" \t\t}, \t\t{ \t\t\t\"name\": \"Kamino\", \t\t\t\"rotation_period\": \"27\", \t\t\t\"orbital_period\": \"463\", \t\t\t\"diameter\": \"19720\", \t\t\t\"climate\": \"temperate\", \t\t\t\"gravity\": \"1 standard\", \t\t\t\"terrain\": \"ocean\", \t\t\t\"surface_water\": \"100\", \t\t\t\"population\": \"1000000000\", \t\t\t\"residents\": [ \t\t\t\t\"https://swapi.dev/api/people/22/\", \t\t\t\t\"https://swapi.dev/api/people/72/\", \t\t\t\t\"https://swapi.dev/api/people/73/\" \t\t\t], \t\t\t\"films\": [ \t\t\t\t\"https://swapi.dev/api/films/5/\" \t\t\t], \t\t\t\"created\": \"2014-12-10T12:45:06.577000Z\", \t\t\t\"edited\": \"2014-12-20T20:58:18.434000Z\", \t\t\t\"url\": \"https://swapi.dev/api/planets/10/\" \t\t} \t] }";

    @BeforeEach
    public void setup() {
        when(planetClient.getAllPlanets(anyInt())).thenReturn(getPlanetsSwapiResponse());
        when(repository.findByIdSwappi(anyInt())).thenReturn(getPlanetEntity());
        when(planetClient.getPlanetsById(anyInt())).thenReturn(getPlanetSwapi());

    }


    @Test
    void should_returnAListBiggerThanZero_when_recordsExistInSwapiApi() {
        List<Planet> response = planetService.getAllPlanets(1);
        int expected = 10;
        int actual = response.size();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAEmptyList_when_recordsDoNotExistInSwapiApi() {
        when(planetClient.getAllPlanets(anyInt())).thenReturn(null);
        List<Planet> response = planetService.getAllPlanets(1);
        int expected = 0;
        int actual = response.size();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAEmptyList_when_swapiApiReturnEmptyList() {
        PlanetsSwapiResponse responseSwapi = new PlanetsSwapiResponse();
        when(planetClient.getAllPlanets(anyInt())).thenReturn(Optional.of(responseSwapi));
        List<Planet> response = planetService.getAllPlanets(1);
        int expected = 0;
        int actual = response.size();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAEmptyList_when_swapiThrowAnException() {
        when(planetClient.getAllPlanets(anyInt())).thenThrow(NullPointerException.class);
        List<Planet> response = planetService.getAllPlanets(1);
        int expected = 0;
        int actual = response.size();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAPlanetFromDb_when_recordExistInsideDb() {
        Optional<Planet> response = planetService.getPlanetById(1);
        String expected = "Tatooine";
        String actual = response.get().getName();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAPlanetFromSwapiApiAndSaveInsideDb_when_idPlanetDoesNotExist() {
        Optional<PlanetEntity> planetEntity = getPlanetEntity();
        planetEntity.get().setId(0);
        when(repository.findByIdSwappi(anyInt())).thenReturn(planetEntity);
        Optional<Planet> response = planetService.getPlanetById(1);
        String expected = "Tatooine";
        String actual = response.get().getName();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnAPlanetFromSwapiApiAndSaveInsideDb_when_recordDoesNotExist() {
        when(repository.findByIdSwappi(anyInt())).thenReturn(Optional.empty());
        Optional<Planet> response = planetService.getPlanetById(1);
        String expected = "Tatooine";
        String actual = response.get().getName();
        assertEquals(expected, actual);
    }

    @Test
    void should_returnEmptyPlanet_when_recordDoesNotExistInSwapiApi() {
        when(repository.findByIdSwappi(anyInt())).thenReturn(Optional.empty());
        when(planetClient.getPlanetsById(anyInt())).thenReturn(Optional.empty());
        Optional<Planet> response = planetService.getPlanetById(1);
        assertFalse(response.isPresent());
    }

    @Test
    void should_returnEmptyPlanet_when_someErrorHappen() {
        when(repository.findByIdSwappi(anyInt())).thenThrow(NullPointerException.class);
        Optional<Planet> response = planetService.getPlanetById(1);
        assertFalse(response.isPresent());
    }

    private Optional<PlanetSwapi> getPlanetSwapi(){
        PlanetSwapi planetSwapi = new PlanetSwapi();
        planetSwapi.setClimate("arid");
        planetSwapi.setDiameter("10465");
        planetSwapi.setGravity("1 standard");
        planetSwapi.setName("Tatooine");
        planetSwapi.setPopulation("200000");
        planetSwapi.setTerrain("desert");
        planetSwapi.setUrl("https://swapi.dev/api/planets/1/");
        planetSwapi.setResidents(new ArrayList<>(){
            {
                add("https://swapi.dev/api/people/1/");
            }
        });
        return Optional.of(planetSwapi);
    }
    private Optional<PlanetsSwapiResponse> getPlanetsSwapiResponse() {
        Gson gson = new Gson();
        PlanetsSwapiResponse response = gson.fromJson(json, PlanetsSwapiResponse.class);
        return Optional.of(response);
    }


    private Optional<PlanetEntity> getPlanetEntity(){
        PlanetEntity planetEntity = new PlanetEntity();
        planetEntity.setId(1);
        planetEntity.setClimate("arid");
        planetEntity.setDiameter("10465");
        planetEntity.setGravity("1 standard");
        planetEntity.setName("Tatooine");
        planetEntity.setPopulation("200000");
        planetEntity.setTerrain("desert");
        planetEntity.setUrl("https://swapi.dev/api/planets/1/");
        return Optional.of(planetEntity);
    }

}