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

    private boolean hasComets;
    private boolean hasAsteroids;
    private boolean hasNebulae;
    private boolean hasBlackHoles;
    
    public StarSystem() {} // required by JPA

    public StarSystem(String name, double age, List<Star> stars, List<Planet> planets, boolean hasComets, boolean hasAsteroids, boolean hasNebulae, boolean hasBlackHoles) {
        this.name = name;
        this.age = age;
        this.stars = stars;
        this.planets = planets;
        this.hasComets = hasComets;
        this.hasAsteroids = hasAsteroids;
        this.hasNebulae = hasNebulae;
        this.hasBlackHoles = hasBlackHoles;
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

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public boolean getHasComets() {
        return hasComets;
    }

    public void setHasComets(boolean hasComets) {
        this.hasComets = hasComets;
    }

    public boolean getHasAsteroids() {
        return hasAsteroids;
    }

    public void setHasAsteroids(boolean hasAsteroids) {
        this.hasAsteroids = hasAsteroids;
    }

    public boolean getHasNebulae() {
        return hasNebulae;
    }

    public void setHasNebulae(boolean hasNebulae) {
        this.hasNebulae = hasNebulae;
    }

    public boolean getHasBlackHoles() {
        return hasBlackHoles;
    }

    public void setHasBlackHoles(boolean hasBlackHoles) {
        this.hasBlackHoles = hasBlackHoles;
    }
}