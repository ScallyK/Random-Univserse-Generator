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
    private double gravity; // in m/s^2
    private int orbitalPeriod; // in days
    private int rotationalPeriod; // in hours
    private double surfaceTemperature; // in Kelvin
    private boolean hasAtmosphere;
    private boolean isTidallyLocked;
    private boolean hasWater;
    private String color;

    protected Moon() {
    } // required by JPA

    public Moon(String name, double diameter, double mass, double gravity, int orbitalPeriod, int rotationalPeriod,
            double surfaceTemperature, boolean hasAtmosphere, boolean isTidallyLocked,
            boolean hasWater, String color) {
        this.name = name;
        this.diameter = diameter;
        this.mass = mass;
        this.gravity = gravity;
        this.orbitalPeriod = orbitalPeriod;
        this.rotationalPeriod = rotationalPeriod;
        this.surfaceTemperature = surfaceTemperature;
        this.hasAtmosphere = hasAtmosphere;
        this.isTidallyLocked = isTidallyLocked;
        this.hasWater = hasWater;
        this.color = color;
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

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public int getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(int orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public int getRotationalPeriod() {
        return rotationalPeriod;
    }

    public void setRotationalPeriod(int rotationalPeriod) {
        this.rotationalPeriod = rotationalPeriod;
    }

    public double getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public void setSurfaceTemperature(double surfaceTemperature) {
        this.surfaceTemperature = surfaceTemperature;
    }

    public boolean isHasAtmosphere() {
        return hasAtmosphere;
    }

    public void setHasAtmosphere(boolean hasAtmosphere) {
        this.hasAtmosphere = hasAtmosphere;
    }

    public boolean isTidallyLocked() {
        return isTidallyLocked;
    }

    public void setTidallyLocked(boolean isTidallyLocked) {
        this.isTidallyLocked = isTidallyLocked;
    }

    public boolean isHasWater() {
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

}
