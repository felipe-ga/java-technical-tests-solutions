package com.gettrx.javatechnicaltestssolutions.config.feign;

import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import com.gettrx.javatechnicaltestssolutions.data.dto.response.PeopleSwapiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
/**
 * @author galindoaguilarf
 * @version 1.0
 */
@FeignClient(name = "peopleClient", url = "${host.url.service.swapi}")
public interface PeopleClient {
    @RequestMapping(method = RequestMethod.GET, value = "${host.uri.service.swapi.people}")
    Optional<PeopleSwapiResponse> getAllPeople(@RequestParam(value = "page") Integer page);

    @RequestMapping(method = RequestMethod.GET, value = "${host.uri.service.swapi.peopleById}")
    Optional<People> getPeopleById(@RequestParam(value = "peopleId") Integer peopleId);
}
