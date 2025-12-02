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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Lifeform> lifeforms;

    private double gravity; // in m/s^2
    private double orbitalPeriod; // in days
    private double rotationalPeriod; // in hours
    private double surfaceTemperature; // in Kelvin
    private boolean hasAtmosphere;
    private boolean hasWater;
    private String color;
    private double averagePressure; // in atm
    private double axialTilt; // in degrees
    private double distanceFromStar; // in million kilometers
    private boolean hasRings;
    private double escapeVelocity; // in km/s

    public Planet(){} // required by JPA
    
    public Planet(String name, PlanetType planetType, double diameter, double mass, List<Moon> moons, Boolean planetHasLife, List<Lifeform> lifeforms, double gravity, double orbitalPeriod, double rotationalPeriod, double surfaceTemperature, boolean hasAtmosphere, boolean hasWater, String color, double averagePressure, double axialTilt, double distanceFromStar, boolean hasRings, double escapeVelocity) {
        this.name = name;
        this.planetType = planetType;
        this.diameter = diameter;
        this.mass = mass;
        this.moons = moons;
        this.planetHasLife = planetHasLife;
        this.lifeforms = lifeforms;
        this.gravity = gravity;
        this.orbitalPeriod = orbitalPeriod;
        this.rotationalPeriod = rotationalPeriod;
        this.surfaceTemperature = surfaceTemperature;
        this.hasAtmosphere = hasAtmosphere;
        this.hasWater = hasWater;
        this.color = color;
        this.averagePressure = averagePressure;
        this.axialTilt = axialTilt;
        this.distanceFromStar = distanceFromStar;
        this.hasRings = hasRings;
        this.escapeVelocity = escapeVelocity;
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

    public List<Lifeform> getLifeforms() {
        return lifeforms;
    }

    public void setLifeforms(List<Lifeform> lifeforms) {
        this.lifeforms = lifeforms;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public double getRotationalPeriod() {
        return rotationalPeriod;
    }

    public void setRotationalPeriod(double rotationalPeriod) {
        this.rotationalPeriod = rotationalPeriod;
    }

    public double getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public void setSurfaceTemperature(double surfaceTemperature) {
        this.surfaceTemperature = surfaceTemperature;
    }

    public boolean getHasAtmosphere() {
        return hasAtmosphere;
    }

    public void setHasAtmosphere(boolean hasAtmosphere) {
        this.hasAtmosphere = hasAtmosphere;
    }

    public boolean getHasWater() {
        return hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getAveragePressure() {
        return averagePressure;
    }

    public void setAveragePressure(double averagePressure) {
        this.averagePressure = averagePressure;
    }

    public double getAxialTilt() {
        return axialTilt;
    }

    public void setAxialTilt(double axialTilt) {
        this.axialTilt = axialTilt;
    }

    public double getDistanceFromStar() {
        return distanceFromStar;
    }

    public void setDistanceFromStar(double distanceFromStar) {
        this.distanceFromStar = distanceFromStar;
    }

    public boolean getHasRings() {
        return hasRings;
    }

    public void setHasRings(boolean hasRings) {
        this.hasRings = hasRings;
    }

    public double getEscapeVelocity() {
        return escapeVelocity;
    }

    public void setEscapeVelocity(double escapeVelocity) {
        this.escapeVelocity = escapeVelocity;
    }
}