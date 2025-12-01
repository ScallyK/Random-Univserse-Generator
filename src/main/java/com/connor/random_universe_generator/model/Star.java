package com.connor.random_universe_generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Star {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private StarType starType;
    
    private double mass; // in kilograms
    private double diameter; // in kilometers
    private double temperature; // in Kelvin

    public Star() {} // required by JPA
    
    public Star(String name, StarType starType, double mass, double diameter, double temperature) {
        this.name = name;
        this.starType = starType;
        this.mass = mass;
        this.diameter = diameter;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StarType getStarType() {
        return starType;
    }

    public double getMass() {
        return mass;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getTemperature() {
        return temperature;
    }
}
