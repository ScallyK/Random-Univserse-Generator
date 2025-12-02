package com.connor.random_universe_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomUniverseGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomUniverseGeneratorApplication.class, args);
		System.out.println("Random Universe Generator started successfully.");
	}

}
