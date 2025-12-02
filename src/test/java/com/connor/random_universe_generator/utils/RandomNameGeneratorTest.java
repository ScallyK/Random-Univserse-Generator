package com.connor.random_universe_generator.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.connor.random_universe_generator.util.RandomNameGenerator;

@SpringBootTest
public class RandomNameGeneratorTest {

    @Test
    public void testGenerateGalaxyName() {
        String name = RandomNameGenerator.generateGalaxyName();
        assertNotNull(name);
        assertTrue(name.length() > 0);
    }

    @Test
    public void testGenerateStarSystemName() {
        String name = RandomNameGenerator.generateStarSystemName();
        assertNotNull(name);
        assertTrue(name.length() > 0);
    }

    @Test
    public void testGenerateStarName() {
        String name = RandomNameGenerator.generateStarName();
        assertNotNull(name);
        assertTrue(name.length() > 0);
    }

    @Test
    public void testGeneratePlanetName() {
        String name = RandomNameGenerator.generatePlanetName();
        assertNotNull(name);
        assertTrue(name.length() > 0);
    }

    @Test
    public void testGenerateMoonName() {
        String name = RandomNameGenerator.generateMoonName();
        assertNotNull(name);
        assertTrue(name.length() > 0);
    }

    @Test
    public void testGenerateLifeformName() {
        String name = RandomNameGenerator.generateLifeformName();
        assertNotNull(name);
        assertTrue(name.length() > 0);
        assertTrue(Character.isUpperCase(name.codePointAt(0)));
    }

    @Test
    public void testRandomHexColorGenerator() {
        String color = RandomNameGenerator.randomHexColorGenerator();
        assertNotNull(color);
        assertTrue(color.matches("^#([A-Fa-f0-9]{6})$"));
    }


}
