package com.connor.random_universe_generator.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class StarSystem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double age; // in billion years

    @OneToMany(cascade = CascadeType.ALL)
    private List<Star> stars;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Planet> planets;
    
    public StarSystem() {} // required by JPA

    public StarSystem(String name, double age, List<Star> stars, List<Planet> planets) {
        this.name = name;
        this.age = age;
        this.stars = stars;
        this.planets = planets;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public List<Star> getStars() {
        return stars;
    }

    public List<Planet> getPlanets() {
        return planets;
    }
}