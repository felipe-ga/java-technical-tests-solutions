package com.gettrx.javatechnicaltestssolutions.data.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public class Planet extends PlanetBase implements Serializable {
    private static final long serialVersionUID = 2387356862575713452L;
    private String residents;

}
