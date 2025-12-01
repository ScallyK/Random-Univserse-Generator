package com.connor.random_universe_generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Moon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    private final String name;
    private final double diameter; // in kilometers
    private final double mass; // in kilograms
    
    public Moon(long id, String name, double diameter, double mass) {
        this.id = id;
        this.name = name;
        this.diameter = diameter;
        this.mass = mass;
    }

    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getMass() {
        return mass;
    }

}
