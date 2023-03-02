package com.gettrx.javatechnicaltestssolutions.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public class PeopleBase implements Serializable {
    @NotNull(message = "name is is required")
    private String name;

    @NotNull(message = "birthYear is is required")
    @JsonProperty("birth_year")
    private String birthYear;

    @NotNull(message = "eyeColor is is required")
    @JsonProperty("eye_color")
    private String eyeColor;

    @NotNull(message = "gender is is required")
    private String gender;

    @NotNull(message = "hairColor is is required")
    @JsonProperty("hair_color")
    private String hairColor;

    @NotNull(message = "height is is required")
    private String height;

    @NotNull(message = "homeWorld is is required")
    @JsonProperty("homeworld")
    private String homeWorld;

    @NotNull(message = "mass is is required")
    private String mass;

    @NotNull(message = "skinColor is is required")
    @JsonProperty("skin_color")
    private String skinColor;

    @NotNull(message = "created is is required")
    private String created;

    @NotNull(message = "edited is is required")
    private String edited;

    private String url;
}
