package com.gettrx.javatechnicaltestssolutions.data.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public class PlanetSwapi extends PlanetBase implements Serializable {
    private static final long serialVersionUID = 2_958_228_741_760_151_497L;
    private List<String> residents;
}
