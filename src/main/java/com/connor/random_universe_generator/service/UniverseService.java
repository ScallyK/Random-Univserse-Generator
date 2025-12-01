package com.connor.random_universe_generator.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.connor.random_universe_generator.model.Moon;
import com.connor.random_universe_generator.model.Planet;
import com.connor.random_universe_generator.model.PlanetType;
import com.connor.random_universe_generator.model.SimpleGalaxy;
import com.connor.random_universe_generator.model.Star;
import com.connor.random_universe_generator.model.StarSystem;
import com.connor.random_universe_generator.model.StarType;

@Service
public class UniverseService {

    private final Random random = new Random();

    // Generates a random single galaxy
    public SimpleGalaxy generateGalaxy() {

        String[] galaxyTypes = {"Spiral", "Elliptical", "Irregular", "Barred Spiral", "Peculiar", "Lenticular"};

        long id = random.nextLong();
        String galaxyName = "Galaxy-" + random.nextInt(1000000); // Change to choose from list
        double age = 1 + random.nextDouble() * 13; // in billion years
        String type = galaxyTypes[random.nextInt(galaxyTypes.length)];

        // Generates between 1 and 1000 star systems
        List<StarSystem> starSystems = java.util.stream.IntStream.range(0, 1 + random.nextInt(1000))
                .mapToObj(i -> generateStarSystem())
                .collect(java.util.stream.Collectors.toList());

        return new SimpleGalaxy(id, galaxyName, age, type, starSystems);
    }

    // Generates a random single solar system
    public StarSystem generateStarSystem() {

        long id = random.nextLong();
        String starSystemName = "Star System-" + random.nextInt(1000000); // Change to choose from list
        double age = 1 + random.nextDouble() * 13; // in billion years

        // Generates between 1 and 7 stars
        List<Star> stars = java.util.stream.IntStream.range(0, 1 + random.nextInt(7))
                .mapToObj(i -> generateStar())
                .collect(java.util.stream.Collectors.toList());

        // Generates between 1 and 12 planets
        int planetCount = 1 + random.nextInt(12);
        List<Planet> planets = java.util.stream.IntStream.range(0, planetCount)
                .mapToObj(i -> generatePlanet())
                .collect(java.util.stream.Collectors.toList());

        return new StarSystem(id, starSystemName, age, stars, planets);
    }

    // Generates a random single star
    public Star generateStar() {
        long id = random.nextLong();
        String starName = "Star-" + random.nextInt(10000); // Change to choose from list
        StarType starType = StarType.values()[random.nextInt(StarType.values().length)];
        double mass = 1e29 + random.nextDouble() * 1e32; // in kilograms
        double diameter = 100000 + random.nextDouble() * 2000000; // in kilometers

        return new Star(id, starName, starType, mass, diameter);
    }

    // Generates a random single planet
    public Planet generatePlanet() {
        long id = random.nextLong();
        String planetName = "Planet-" + random.nextInt(10000); // Change to choose from list
        PlanetType planetType = PlanetType.values()[random.nextInt(PlanetType.values().length)];
        double diameter = 1000 + random.nextDouble() * 100000; // in kilometers
        double mass = 1e20 + random.nextDouble() * 1e30;

        // Generates between 0 and 5 moons
        List<Moon> moons = java.util.stream.IntStream.range(0, random.nextInt(6))
                .mapToObj(i -> generateMoon())
                .collect(java.util.stream.Collectors.toList());
        Boolean hasLife = random.nextBoolean(); // change to also be null sometimes

        return new Planet(id, planetName, planetType, diameter, mass, moons, hasLife);
    }

    // Generates a random single moon
    public Moon generateMoon() {
        long id = random.nextLong();
        String moonName = "Moon-" + random.nextInt(10000);
        double diameter = 10 + random.nextDouble() * 5000; // in kilometers
        double mass = 1e15 + random.nextDouble() * 1e22;

        return new Moon(id, moonName, diameter, mass);
    }

}
