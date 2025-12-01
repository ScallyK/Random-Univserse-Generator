package com.connor.random_universe_generator.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    private final String name;
    private final PlanetType planetType;
    private final double diameter; // in kilometers
    private final double mass; // in kilograms
    
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Moon> moons;
    private final Boolean planetHasLife; // null if unknown
    public Planet(long id, String name, PlanetType planetType, double diameter, double mass, List<Moon> moons, Boolean planetHasLife) {
        this.id = id;
        this.name = name;
        this.planetType = planetType;
        this.diameter = diameter;
        this.mass = mass;
        this.moons = moons;
        this.planetHasLife = planetHasLife;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlanetType getPlanetType() {
        return planetType;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getMass() {
        return mass;
    }

    public List<Moon> getMoons() {
        return moons;
    }

    public Boolean getPlanetHasLife() {
        return planetHasLife;
    }

}