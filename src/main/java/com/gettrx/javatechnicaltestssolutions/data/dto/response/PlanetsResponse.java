package com.gettrx.javatechnicaltestssolutions.data.dto.response;

import com.gettrx.javatechnicaltestssolutions.data.dto.Planet;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public class PlanetsResponse implements Serializable {
    private static final long serialVersionUID = -1119147123924908985L;
    private List<Planet> planets;
}
