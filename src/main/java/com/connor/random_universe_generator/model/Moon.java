package com.connor.random_universe_generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Moon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double diameter; // in kilometers
    private double mass; // in kilograms

    protected Moon() {} // required by JPA
    
    public Moon(String name, double diameter, double mass) {
        this.name = name;
        this.diameter = diameter;
        this.mass = mass;
    }

    public Long getId() {
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
