package com.gettrx.javatechnicaltestssolutions.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author galindoaguilarf
 * @version 1.0
 */
@Entity
@Table(name = "planet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "idSwappi is is required")
    private Integer idSwappi;

    @NotEmpty(message = "climate is is required")
    private String climate;

    @NotEmpty(message = "diameter is is required")
    private String diameter;

    @NotEmpty(message = "gravity is is required")
    private String gravity;

    @NotEmpty(message = "name is is required")
    private String name;

    @NotEmpty(message = "population is is required")
    private String population;

    @NotEmpty(message = "residents is is required")
    @Column(columnDefinition = "text")
    @Type(type = "org.hibernate.type.TextType")
    private String residents;

    @NotEmpty(message = "terrain is is required")
    private String terrain;

    private String url;
}
