package com.gettrx.javatechnicaltestssolutions.data.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public abstract class PlanetBase implements Serializable {
    private static final long serialVersionUID = -3582070616070976810L;
    private String climate;
    private String diameter;
    private String gravity;
    private String name;
    private String population;
    private String terrain;
    private String url;
}
