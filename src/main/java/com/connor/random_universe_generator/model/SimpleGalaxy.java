package com.connor.random_universe_generator.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SimpleGalaxy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    private final String name;
    private final double age; // in billion years
    private final String type;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<StarSystem> starSystems;

    public SimpleGalaxy(long id, String name, double age, String type, List<StarSystem> starSystems) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.starSystems = starSystems;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public List<StarSystem> getStarSystems() {
        return starSystems;
    }
}
