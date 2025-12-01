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
    private final long id;

    private final String name;
    private final double age; // in billion years

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Star> stars;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Planet> planets;
    
    public StarSystem(long id, String name, double age, List<Star> stars, List<Planet> planets) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.stars = stars;
        this.planets = planets;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Star> getStars() {
        return stars;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public double getAge() {
        return age;
    }

}
