package com.gettrx.javatechnicaltestssolutions.data.dto.response;


import com.gettrx.javatechnicaltestssolutions.data.dto.PeopleBase;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public class PeopleResponse implements Serializable {
    private static final long serialVersionUID = 8897373370107601633L;
    private List<PeopleBase> people;
}
