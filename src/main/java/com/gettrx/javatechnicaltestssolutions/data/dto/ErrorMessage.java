package com.gettrx.javatechnicaltestssolutions.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1109382693179607075L;
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
