package com.connor.random_universe_generator.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connor.random_universe_generator.model.Lifeform;
import com.connor.random_universe_generator.model.Moon;
import com.connor.random_universe_generator.model.Planet;
import com.connor.random_universe_generator.model.PlanetType;
import com.connor.random_universe_generator.model.SimpleGalaxy;
import com.connor.random_universe_generator.model.SimpleUniverse;
import com.connor.random_universe_generator.model.Star;
import com.connor.random_universe_generator.model.StarSystem;
import com.connor.random_universe_generator.model.StarType;
import com.connor.random_universe_generator.repository.GalaxyRepository;
import com.connor.random_universe_generator.repository.MoonRepository;
import com.connor.random_universe_generator.repository.PlanetRepository;
import com.connor.random_universe_generator.repository.StarRepository;
import com.connor.random_universe_generator.repository.StarSystemRepository;
import com.connor.random_universe_generator.repository.UniverseRepository;
import com.connor.random_universe_generator.util.JsonFileUtil;
import com.connor.random_universe_generator.util.RandomNameGenerator;

@Service
public class UniverseService {

    // Random number generator
    private final Random random = new Random();

    // Inject repositories for database operations
    private final UniverseRepository universeRepository;

    // Constructor-based dependency injection
    @Autowired
    public UniverseService(UniverseRepository universeRepository, GalaxyRepository galaxyRepository, StarSystemRepository starSystemRepository, StarRepository starRepository, PlanetRepository planetRepository, MoonRepository moonRepository) {
        this.universeRepository = universeRepository;
    }

    // Generates a random universe
    public SimpleUniverse generateUniverse(boolean saveToDatabase, boolean saveToFile) {

        List<SimpleGalaxy> galaxies = java.util.stream.IntStream.range(0, 1 + random.nextInt(10))
                .mapToObj(i -> generateGalaxy())
                .collect(java.util.stream.Collectors.toList());

        var universe = new SimpleUniverse(galaxies);

        if (saveToDatabase) {
            universe = universeRepository.save(universe);
        }

        if (saveToFile) {
            JsonFileUtil.writeToFile(universe, "data/output/universe-" + System.currentTimeMillis() + ".json");
        }

        return universe;
    }

    // Generates a random single galaxy
    public SimpleGalaxy generateGalaxy() {

        String[] galaxyTypes = {"Spiral", "Elliptical", "Irregular", "Barred Spiral", "Peculiar", "Lenticular"};
        String galaxyName = RandomNameGenerator.generateGalaxyName();
        double age = 1 + random.nextDouble() * 13; // in billion years
        String type = galaxyTypes[random.nextInt(galaxyTypes.length)];

        // Generates between 1 and 1000 star systems
        List<StarSystem> starSystems = java.util.stream.IntStream.range(0, 1 + random.nextInt(1000))
                .mapToObj(i -> generateStarSystem())
                .collect(java.util.stream.Collectors.toList());

        SimpleGalaxy galaxy = new SimpleGalaxy(galaxyName, age, type, starSystems);

        return galaxy;
    }

    // Generates a random single solar system
    public StarSystem generateStarSystem() {

        String starSystemName = RandomNameGenerator.generateStarSystemName();
        double age = 1 + random.nextDouble() * 13; // in billion years

        // Generates between 1 and 7 stars
        List<Star> stars = java.util.stream.IntStream.range(0, 1 + random.nextInt(7))
                .mapToObj(i -> generateStar())
                .collect(java.util.stream.Collectors.toList());

        // Generates between 0 and 12 planets
        int planetCount = 0 + random.nextInt(12);
        List<Planet> planets = java.util.stream.IntStream.range(0, planetCount)
                .mapToObj(i -> generatePlanet())
                .collect(java.util.stream.Collectors.toList());

        StarSystem starSystem = new StarSystem(starSystemName, age, stars, planets);

        return starSystem;
    }

    // Generates a random single star
    public Star generateStar() {

        String starName = RandomNameGenerator.generateStarName();
        StarType starType = StarType.values()[random.nextInt(StarType.values().length)];
        double mass = 1e29 + random.nextDouble() * 1e32; // in kilograms
        double diameter = 100000 + random.nextDouble() * 2000000; // in kilometers
        double temperature = 2000 + random.nextDouble() * 30000; // in Kelvin

        Star star = new Star(starName, starType, mass, diameter, temperature);

        return star;
    }

    // Generates a random single planet
    public Planet generatePlanet() {

        String planetName = RandomNameGenerator.generatePlanetName();
        PlanetType planetType = PlanetType.values()[random.nextInt(PlanetType.values().length)];
        double diameter = 1000 + random.nextDouble() * 100000; // in kilometers
        double mass = 1e20 + random.nextDouble() * 1e30;

        // Generates between 0 and 5 moons
        List<Moon> moons = java.util.stream.IntStream.range(0, random.nextInt(6))
                .mapToObj(i -> generateMoon())
                .collect(java.util.stream.Collectors.toList());
        Boolean hasLife = random.nextBoolean(); // change to also be null sometimes

        // Generates between 0 and 10 lifeforms if the planet has life
        Planet planet = new Planet(planetName, planetType, diameter, mass, moons, hasLife,
                hasLife ? java.util.stream.IntStream.range(0, 1 + random.nextInt(10))
                                .mapToObj(i -> generateLifeform())
                                .collect(java.util.stream.Collectors.toList()) : null);

        return planet;
    }

    // Generates a random single moon
    public Moon generateMoon() {

        String moonName = RandomNameGenerator.generateMoonName();
        double diameter = 10 + random.nextDouble() * 5000; // in kilometers
        double mass = 1e15 + random.nextDouble() * 1e22;

        Moon moon = new Moon(moonName, diameter, mass);

        return moon;
    }

    // Generates a random single lifeform
    public Lifeform generateLifeform() {

        String[] kardashevScales = {"Type I", "Type II", "Type III"};
        String[] habitats = {"terrestrial", "aquatic", "aerial", "subterranean", "amphibious"};
        String[] diets = {"herbivore", "carnivore", "omnivore", "phototroph", "chemosynthetic"};
        String[] reproductionMethods = {"sexual", "asexual", "budding", "spore-based", "binary fission"};

        String name = RandomNameGenerator.generateLifeformName();
        int lifespan = 1 + random.nextInt(1000); // in years
        boolean intelligent = random.nextBoolean();
        double averageHeight = 0.5 + random.nextDouble() * 3.0; // in meters
        double averageWeight = 5.0 + random.nextDouble() * 500.0; // in kilograms

        String kardashevScale = kardashevScales[random.nextInt(kardashevScales.length)];

        String habitat = habitats[random.nextInt(habitats.length)];

        String diet = diets[random.nextInt(diets.length)];

        String reproductionMethod = reproductionMethods[random.nextInt(reproductionMethods.length)];

        Lifeform lifeform = new Lifeform(name, lifespan, intelligent, averageHeight, averageWeight, kardashevScale, habitat, diet, reproductionMethod);

        return lifeform;
    }

}
