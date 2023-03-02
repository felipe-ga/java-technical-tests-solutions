package com.gettrx.javatechnicaltestssolutions.data.dto.response;

import com.gettrx.javatechnicaltestssolutions.data.dto.People;
import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public class PeopleSwapiResponse implements Serializable {
    private static final long serialVersionUID = 8986497027767594043L;
    private List<PeopleBase> results;
    public PeopleSwapiResponse(){
        this.results = new ArrayList<>();
    }
}
