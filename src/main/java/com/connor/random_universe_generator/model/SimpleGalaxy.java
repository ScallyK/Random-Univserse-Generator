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
    private Long id;

    private String name;
    private double age; // in billion years
    private String type;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StarSystem> starSystems;

    public SimpleGalaxy() {} // required by JPA

    public SimpleGalaxy(Long id, String name, double age, String type, List<StarSystem> starSystems) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.starSystems = starSystems;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StarSystem> getStarSystems() {
        return starSystems;
    }

    public void setStarSystems(List<StarSystem> starSystems) {
        this.starSystems = starSystems;
    }
}
