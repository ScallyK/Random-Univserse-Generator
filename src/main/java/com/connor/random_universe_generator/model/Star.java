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
    private double luminosity; // in Watts
    private double radius; // in kilometers
    private double age; // in billion years
    private double metallacity; // proportion of elements heavier than helium

    public Star() {} // required by JPA

    public Star(String name, StarType starType, double mass, double diameter, double temperature, double luminosity, double radius, double age, double metallacity) {
        this.name = name;
        this.starType = starType;
        this.mass = mass;
        this.diameter = diameter;
        this.temperature = temperature;
        this.luminosity = luminosity;
        this.radius = radius;
        this.age = age;
        this.metallacity = metallacity;
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

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }
    
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getMetallacity() {
        return metallacity;
    }

    public void setMetallacity(double metallacity) {
        this.metallacity = metallacity;
    }

}
