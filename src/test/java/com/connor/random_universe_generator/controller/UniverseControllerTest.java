package com.connor.random_universe_generator.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.connor.random_universe_generator.model.Moon;
import com.connor.random_universe_generator.model.Planet;
import com.connor.random_universe_generator.model.PlanetType;
import com.connor.random_universe_generator.model.SimpleGalaxy;
import com.connor.random_universe_generator.model.SimpleUniverse;
import com.connor.random_universe_generator.model.Star;
import com.connor.random_universe_generator.model.StarSystem;
import com.connor.random_universe_generator.model.StarType;
import com.connor.random_universe_generator.service.UniverseService;

@SpringBootTest
@AutoConfigureMockMvc
public class UniverseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UniverseService universeService;

    @Test
    public void testGenerateRandomUniverse() throws Exception {
        SimpleUniverse universe = new SimpleUniverse(null);
        universe.setId(1L);

        Mockito.when(universeService.generateUniverse(false, false)).thenReturn(universe);

        mockMvc.perform(get("/universe/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.galaxies").value((Object) null))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGenerateRandomGalaxy() throws Exception {
        SimpleGalaxy galaxy = new SimpleGalaxy(1L, "TestGalaxy", 4.6, "Elliptical", null);

        Mockito.when(universeService.generateGalaxy(false)).thenReturn(galaxy);

        mockMvc.perform(get("/galaxy/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestGalaxy"))
                .andExpect(jsonPath("$.age").value(4.6))
                .andExpect(jsonPath("$.type").value("Elliptical"))
                .andExpect(jsonPath("$.starSystems").value((Object) null))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGenerateRandomStarSystem() throws Exception {
        StarSystem starSystem = new StarSystem(1L, "TestStarSystem", 4.6, null, null, false, false, false, false);
        starSystem.setId(1L);

        Mockito.when(universeService.generateStarSystem(false)).thenReturn(starSystem);

        mockMvc.perform(get("/star_system/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestStarSystem"))
                .andExpect(jsonPath("$.age").value(4.6))
                .andExpect(jsonPath("$.stars").value((Object) null))
                .andExpect(jsonPath("$.planets").value((Object) null))
                .andExpect(jsonPath("$.hasComets").value(false))
                .andExpect(jsonPath("$.hasAsteroids").value(false))
                .andExpect(jsonPath("$.hasNebulae").value(false))
                .andExpect(jsonPath("$.hasBlackHoles").value(false))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGenerateRandomStar() throws Exception {
        Star star = new Star(1L, "TestStar", StarType.BLUE_GIANT, 1.989e30, 1.3927e6, 5778, 3.828e26, 696340, 4.6, 0.012);
        star.setId(1L);

        Mockito.when(universeService.generateStar(false)).thenReturn(star);

        mockMvc.perform(get("/star/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestStar"))
                .andExpect(jsonPath("$.starType").value("BLUE_GIANT"))
                .andExpect(jsonPath("$.mass").value(1.989e30))
                .andExpect(jsonPath("$.diameter").value(1.3927e6))
                .andExpect(jsonPath("$.temperature").value(5778))
                .andExpect(jsonPath("$.luminosity").value(3.828e26))
                .andExpect(jsonPath("$.radius").value(696340))
                .andExpect(jsonPath("$.age").value(4.6))
                .andExpect(jsonPath("$.metallacity").value(0.012))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGenerateRandomPlanet() throws Exception {
        Planet planet = new Planet(1L, "TestPlanet", PlanetType.TERRESTRIAL, 1000, 1e20, null, false, null, 9.8, 365, 24, 288, true, false, "#FF5733", 101.3, 23.5, 1.496e8, false, 11.2);
        planet.setId(1L);

        Mockito.when(universeService.generatePlanet(false)).thenReturn(planet);

        mockMvc.perform(get("/planet/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestPlanet"))
                .andExpect(jsonPath("$.planetType").value("TERRESTRIAL"))
                .andExpect(jsonPath("$.diameter").value(1000.0))
                .andExpect(jsonPath("$.mass").value(1e20))
                .andExpect(jsonPath("$.planetHasLife").value(false))
                .andExpect(jsonPath("$.moons").value((Object) null))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGenerateRandomMoon() throws Exception {
        Moon moon = new Moon(1L, "TestMoon", 1000, 1e20, 0.5, 27, 655, 250, true, false, true, "#AAAAAA");
        moon.setId(1L);

        Mockito.when(universeService.generateMoon(false)).thenReturn(moon);

        mockMvc.perform(get("/moon/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestMoon"))
                .andExpect(jsonPath("$.diameter").value(1000.0))
                .andExpect(jsonPath("$.mass").value(1e20))
                .andExpect(jsonPath("$.gravity").value(0.5))
                .andExpect(jsonPath("$.orbitalPeriod").value(27))
                .andExpect(jsonPath("$.rotationalPeriod").value(655))
                .andExpect(jsonPath("$.surfaceTemperature").value(250))
                .andExpect(jsonPath("$.hasAtmosphere").value(true))
                .andExpect(jsonPath("$.hasWater").value(true))
                .andExpect(jsonPath("$.color").value("#AAAAAA"))
                .andExpect(jsonPath("$.id").value(1));
    }
}
