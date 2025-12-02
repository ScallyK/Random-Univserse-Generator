package com.connor.random_universe_generator.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.connor.random_universe_generator.model.Moon;
import com.connor.random_universe_generator.model.Planet;
import com.connor.random_universe_generator.model.SimpleGalaxy;
import com.connor.random_universe_generator.model.SimpleUniverse;
import com.connor.random_universe_generator.model.Star;
import com.connor.random_universe_generator.model.StarSystem;

@SpringBootTest
public class UniverseServiceTest {

    @Autowired
    private UniverseService universeService;

    @Test
    public void testGenerateUniverse() {
        SimpleUniverse universe = universeService.generateUniverse(false, false);
        assertNotNull(universe);
        assertNotNull(universe.getGalaxies());
        assertTrue(!universe.getGalaxies().isEmpty());

        // Verify at least one galaxy has a star system
        SimpleGalaxy galaxy = universe.getGalaxies().get(0);
        assertNotNull(galaxy.getStarSystems());
        assertTrue(!galaxy.getStarSystems().isEmpty());

        // Verify at least one star system has at least one star
        StarSystem starSystem = galaxy.getStarSystems().get(0);
        assertNotNull(starSystem.getStars());
        assertTrue(!starSystem.getStars().isEmpty());

        // Verify at least one star system has at least one star
        Star star = starSystem.getStars().get(0);
        assertNotNull(star.getName());
    }

    @Test
    public void testGenerateGalaxy() {
        SimpleGalaxy galaxy = universeService.generateGalaxy();
        assertNotNull(galaxy);
        assertNotNull(galaxy.getName());
        assertNotNull(galaxy.getAge());
        assertTrue(galaxy.getAge() > 0);
        assertNotNull(galaxy.getType());
        assertNotNull(galaxy.getStarSystems());
        assertTrue(!galaxy.getStarSystems().isEmpty());
    }

    @Test
    public void testGenerateStarSystem() {
        StarSystem starSystem = universeService.generateStarSystem();
        assertNotNull(starSystem);
        assertNotNull(starSystem.getName());
        assertNotNull(starSystem.getStars());
        assertNotNull(starSystem.getPlanets());
        assertNotNull(starSystem.getAge());
        assertTrue(starSystem.getAge() > 0);
        assertTrue(!starSystem.getPlanets().isEmpty());
        assertTrue(!starSystem.getStars().isEmpty());
    }

    @Test
    public void testGenerateStar() {
        Star star = universeService.generateStar();
        assertNotNull(star);
        assertNotNull(star.getName());
        assertNotNull(star.getStarType());
        assertTrue(star.getMass() > 0);
        assertTrue(star.getDiameter() > 0);
        assertTrue(star.getTemperature() > 0);
    }

    @Test
    public void testGeneratePlanet() {
        Planet planet = universeService.generatePlanet();
        assertNotNull(planet);
        assertNotNull(planet.getName());
        assertNotNull(planet.getPlanetType());
        assertTrue(planet.getDiameter() > 0);
        assertTrue(planet.getMass() > 0);
        assertNotNull(planet.getMoons());
        assertTrue(planet.getMoons().size() >= 0);
    }

    @Test
    public void testGenerateMoon() {
        Moon moon = universeService.generateMoon();
        assertNotNull(moon);
        assertNotNull(moon.getName());
        assertTrue(moon.getDiameter() > 0);
        assertTrue(moon.getMass() > 0);
    }
}
