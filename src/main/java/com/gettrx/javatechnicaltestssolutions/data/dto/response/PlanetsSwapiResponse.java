package com.gettrx.javatechnicaltestssolutions.data.dto.response;

import com.gettrx.javatechnicaltestssolutions.data.dto.PlanetSwapi;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public class PlanetsSwapiResponse implements Serializable {
    private static final long serialVersionUID = 7666926007441713185L;
    private List<PlanetSwapi> results;

    public PlanetsSwapiResponse() {
        this.results = new ArrayList<>();
    }
}
