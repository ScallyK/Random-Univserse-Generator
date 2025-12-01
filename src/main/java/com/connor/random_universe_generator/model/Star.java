package com.connor.random_universe_generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Star {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    private final String name;
    private final StarType starType;
    private final double mass; // in kilograms
    private final double diameter; // in kilometers
    
    public Star(long id, String name, StarType starType, double mass, double diameter) {
        this.id = id;
        this.name = name;
        this.starType = starType;
        this.mass = mass;
        this.diameter = diameter;
    }

    public long getId() {
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
}
