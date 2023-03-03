package com.gettrx.javatechnicaltestssolutions.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Entity
@Table(name = "people")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @SequenceGenerator(name = "peopleSequence", sequenceName = "peopleSequence", initialValue = 10000, allocationSize = 100)
    @GeneratedValue(generator = "peopleSequence")
    private Integer idSwappi;

    @NotEmpty(message = "name is is required")
    private String name;

    @NotEmpty(message = "birthYear is is required")
    @Column(name = "birth_year")
    private String birthYear;

    @NotEmpty(message = "eyeColor is is required")
    @Column(name = "eye_color")
    private String eyeColor;

    @NotEmpty(message = "gender is is required")
    private String gender;

    @NotEmpty(message = "hairColor is is required")
    @Column(name = "hair_color")
    private String hairColor;

    @NotEmpty(message = "height is is required")
    private String height;

    @NotEmpty(message = "homeWorld is is required")
    @Column(name = "home_world")
    private String homeWorld;

    @NotEmpty(message = "mass is is required")
    private String mass;

    @NotEmpty(message = "skinColor is is required")
    @Column(name = "skin_color")
    private String skinColor;

    @NotEmpty(message = "created is is required")
    private String created;

    @NotEmpty(message = "edited is is required")
    private String edited;

    private String url;
}
