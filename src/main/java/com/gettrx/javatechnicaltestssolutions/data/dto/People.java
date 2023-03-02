package com.gettrx.javatechnicaltestssolutions.data.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Data
public class People extends PeopleBase implements Serializable {
    private static final long serialVersionUID = -1464618399491968432L;

    private Integer id;

}
