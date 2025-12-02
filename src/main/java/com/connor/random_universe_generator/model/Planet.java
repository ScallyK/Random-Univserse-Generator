package com.connor.random_universe_generator.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PlanetType planetType;

    private double diameter; // in kilometers
    private double mass; // in kilograms
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Moon> moons;

    private Boolean planetHasLife; // null if unknown

    public Planet(){} // required by JPA
    
    public Planet(String name, PlanetType planetType, double diameter, double mass, List<Moon> moons, Boolean planetHasLife) {
        this.name = name;
        this.planetType = planetType;
        this.diameter = diameter;
        this.mass = mass;
        this.moons = moons;
        this.planetHasLife = planetHasLife;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlanetType getPlanetType() {
        return planetType;
    }

    public void setPlanetType(PlanetType planetType) {
        this.planetType = planetType;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public List<Moon> getMoons() {
        return moons;
    }

    public void setMoons(List<Moon> moons) {
        this.moons = moons;
    }

    public Boolean getPlanetHasLife() {
        return planetHasLife;
    }

    public void setPlanetHasLife(Boolean planetHasLife) {
        this.planetHasLife = planetHasLife;
    }
}