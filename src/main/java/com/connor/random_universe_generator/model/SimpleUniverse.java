package com.connor.random_universe_generator.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SimpleUniverse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SimpleGalaxy> galaxies;

    public SimpleUniverse() {} // required by JPA
    
    public SimpleUniverse(List<SimpleGalaxy> galaxies) {
        this.galaxies = galaxies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SimpleGalaxy> getGalaxies() {
        return galaxies;
    }

    public void setGalaxies(List<SimpleGalaxy> galaxies) {
        this.galaxies = galaxies;
    }
}
