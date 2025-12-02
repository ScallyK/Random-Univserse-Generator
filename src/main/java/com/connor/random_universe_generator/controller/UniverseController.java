package com.connor.random_universe_generator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connor.random_universe_generator.model.Moon;
import com.connor.random_universe_generator.model.Planet;
import com.connor.random_universe_generator.model.SimpleGalaxy;
import com.connor.random_universe_generator.model.SimpleUniverse;
import com.connor.random_universe_generator.model.Star;
import com.connor.random_universe_generator.model.StarSystem;
import com.connor.random_universe_generator.repository.GalaxyRepository;
import com.connor.random_universe_generator.repository.MoonRepository;
import com.connor.random_universe_generator.repository.PlanetRepository;
import com.connor.random_universe_generator.repository.StarRepository;
import com.connor.random_universe_generator.repository.StarSystemRepository;
import com.connor.random_universe_generator.repository.UniverseRepository;
import com.connor.random_universe_generator.service.UniverseService;

@RestController
public class UniverseController {

    private final UniverseService universeService;
    private final UniverseRepository universeRepository;
    private final GalaxyRepository galaxyRepository;
    private final StarSystemRepository starSystemRepository;
    private final StarRepository starRepository;
    private final PlanetRepository planetRepository;
    private final MoonRepository moonRepository;

    public UniverseController(UniverseService universeService, UniverseRepository universeRepository, GalaxyRepository galaxyRepository, StarSystemRepository starSystemRepository, StarRepository starRepository, PlanetRepository planetRepository, MoonRepository moonRepository) {
        this.universeService = universeService;
        this.universeRepository = universeRepository;
        this.galaxyRepository = galaxyRepository;
        this.starSystemRepository = starSystemRepository;
        this.starRepository = starRepository;
        this.planetRepository = planetRepository;
        this.moonRepository = moonRepository;
    }

    // Basic health check endpoint
    @GetMapping("/health")
    public String healthCheck() {
        return "Universe Service is up and running!";
    }

    // Main enddpoint for universe service
    @GetMapping("/universe")
    public String Universe() {
        return "Welcome to the Random Universe Generator!";
    }

    // Endpoint for generating a full random universe
    @GetMapping("/universe/random")
    public SimpleUniverse generateUniverse(@RequestParam(name = "saveToDatabase", defaultValue = "false") boolean saveToDatabase, @RequestParam(name = "saveToFile", defaultValue = "false") boolean saveToFile) {
        return universeService.generateUniverse(saveToDatabase, saveToFile);
    }

    // Endpoint for retrieving a universe by ID. If an id is not provided, defaults to 1
    @GetMapping("/universe/fetch")
    public SimpleUniverse getUniverse(@RequestParam(name = "id", required = false, defaultValue = "1") long id) {
        return universeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Universe not found"));
    }

    // Endpoint for generating a single random simple galaxy (< 1000 star systems)
    @GetMapping("/galaxy/random")
    public SimpleGalaxy generateGalaxy() {
        return universeService.generateGalaxy();
    }

    // Endpoint for retrieving a galaxy by ID. If an id is not provided, defaults to 1
    @GetMapping("/galaxy/fetch")
    public SimpleGalaxy getGalaxy(@RequestParam(name = "id", required = false, defaultValue = "1") long id) {
        return galaxyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Galaxy not found"));
    }

    // Endpoint for generating a single random star system
    @GetMapping("/star_system/random")
    public StarSystem generateStarSystem() {
        return universeService.generateStarSystem();
    }

    // Endpoint for retrieving a star system by ID. If an id is not provided, defaults to 1
    @GetMapping("/star_system/fetch")
    public StarSystem getStarSystem(@RequestParam(name = "id", required = false, defaultValue = "1") long id) {
        return starSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Star System not found"));
    }

    // Endpoint for generating a single random star
    @GetMapping("/star/random")
    public Star generateStar() {
        return universeService.generateStar();
    }

    // Endpoint for retrieving a star by ID. If an id is not provided, defaults to 1
    @GetMapping("/star/fetch")
    public Star getStar(@RequestParam(name = "id", required = false, defaultValue = "1") long id) {
        return starRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Star not found"));
    }

    // Endpoint for generating a single random planet
    @GetMapping("/planet/random")
    public Planet generatePlanet() {
        return universeService.generatePlanet();
    }

    // Endpoint for retrieving a planet by ID. If an id is not provided, defaults to 1
    @GetMapping("/planet/fetch")
    public Planet getPlanet(@RequestParam(name = "id", required = false, defaultValue = "1") long id) {
        return planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found"));
    }

    // Endpoint for generating a single random moon
    @GetMapping("/moon/random")
    public Moon generateMoon() {
        return universeService.generateMoon();
    }

    // Endpoint for retrieving a moon by ID. If an id is not provided, defaults to 1
    @GetMapping("/moon/fetch")
    public Moon getMoon(@RequestParam(name = "id", required = false, defaultValue = "1") long id) {
        return moonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moon not found"));
    }

}
