package com.gettrx.javatechnicaltestssolutions.mapper;

import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import com.gettrx.javatechnicaltestssolutions.data.entity.PeopleEntity;


public class PeopleMapper {
    public static PeopleEntity peopleToPeopleEntity(PeopleBase people, Integer peopleId) {
        return PeopleEntity
                .builder()
                .idSwappi(peopleId)
                .name(people.getName())
                .birthYear(people.getBirthYear())
                .eyeColor(people.getEyeColor())
                .gender(people.getGender())
                .hairColor(people.getHairColor())
                .height(people.getHeight())
                .homeWorld(people.getHomeWorld())
                .mass(people.getMass())
                .skinColor(people.getSkinColor())
                .created(people.getCreated())
                .edited(people.getEdited())
                .url(people.getUrl())
                .build();
    }

    public static People peopleEntityToPeople(PeopleEntity peopleEntity) {
        People people = new People();
        people.setId(peopleEntity.getId());
        people.setName(peopleEntity.getName());
        people.setBirthYear(peopleEntity.getBirthYear());
        people.setEyeColor(peopleEntity.getEyeColor());
        people.setGender(peopleEntity.getGender());
        people.setHairColor(peopleEntity.getHairColor());
        people.setHeight(peopleEntity.getHeight());
        people.setHomeWorld(peopleEntity.getHomeWorld());
        people.setMass(peopleEntity.getMass());
        people.setSkinColor(peopleEntity.getSkinColor());
        people.setCreated(peopleEntity.getCreated());
        people.setEdited(peopleEntity.getEdited());
        people.setUrl(peopleEntity.getUrl());
        return people;
    }
}
