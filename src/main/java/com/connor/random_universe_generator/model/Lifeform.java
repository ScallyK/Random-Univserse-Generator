package com.connor.random_universe_generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lifeform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int lifespan; // in years
    private boolean intelligent;
    private double averageHeight; // in meters
    private double averageWeight; // in kilograms
    private String kardashevScale; // Type I, Type II, Type III
    private String habitat; // terrestrial, aquatic, aerial, etc.
    private String diet; // herbivore, carnivore, omnivore, etc.
    private String reproductionMethod; // sexual, asexual, etc.

    protected Lifeform() {
    } // required by JPA

    public Lifeform(String name, int lifespan, boolean intelligent,
            double averageHeight, double averageWeight, String kardashevScale,
            String habitat, String diet, String reproductionMethod) {
        this.name = name;
        this.lifespan = lifespan;
        this.intelligent = intelligent;
        this.averageHeight = averageHeight;
        this.averageWeight = averageWeight;
        this.kardashevScale = kardashevScale;
        this.habitat = habitat;
        this.diet = diet;
        this.reproductionMethod = reproductionMethod;
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

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    public boolean isIntelligent() {
        return intelligent;
    }

    public void setIntelligent(boolean intelligent) {
        this.intelligent = intelligent;
    }

    public double getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(double averageHeight) {
        this.averageHeight = averageHeight;
    }

    public double getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(double averageWeight) {
        this.averageWeight = averageWeight;
    }

    public String getKardashevScale() {
        return kardashevScale;
    }

    public void setKardashevScale(String kardashevScale) {
        this.kardashevScale = kardashevScale;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getReproductionMethod() {
        return reproductionMethod;
    }

    public void setReproductionMethod(String reproductionMethod) {
        this.reproductionMethod = reproductionMethod;
    }
}
