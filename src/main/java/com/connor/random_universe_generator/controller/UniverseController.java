package com.connor.random_universe_generator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connor.random_universe_generator.model.SimpleGalaxy;
import com.connor.random_universe_generator.service.UniverseService;

@RestController
public class UniverseController {

    private final UniverseService universeService;

    public UniverseController(UniverseService universeService) {
        this.universeService = universeService;
    }

    // Main enddpoint for universe service
    @GetMapping("/universe")
    public String generateUniverse() {
        return "Welcome to the Random Universe Generator!";
    }

    // Endpoint for generating a single simple galaxy (< 1000 star systems)
    @GetMapping("/generate-galaxy")
    public SimpleGalaxy generateGalaxy() {
        return universeService.generateGalaxy();
    }
}


