package com.gettrx.javatechnicaltestssolutions.config.feign;

import com.gettrx.javatechnicaltestssolutions.data.dto.response.PlanetsSwapiResponse;
import com.gettrx.javatechnicaltestssolutions.data.dto.PlanetSwapi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@FeignClient(name = "planetclient", url = "${host.url.service.swapi}")
public interface PlanetClient {
    @RequestMapping(method = RequestMethod.GET, value = "${host.uri.service.swapi.planets}")
    Optional<PlanetsSwapiResponse> getAllPlanets(@RequestParam(value = "page") Integer page);

    @RequestMapping(method = RequestMethod.GET, value = "${host.uri.service.swapi.planetsById}")
    Optional<PlanetSwapi> getPlanetsById(@RequestParam(value = "planetId") Integer planetId);
}
