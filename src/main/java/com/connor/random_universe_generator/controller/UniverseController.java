package com.connor.random_universe_generator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniverseController {

    @GetMapping("/universe")
    public String generateUniverse() {
        return "Welcome to the Random Universe Generator!";
    }
}


