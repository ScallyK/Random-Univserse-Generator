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
        SimpleGalaxy galaxy = new SimpleGalaxy("TestGalaxy", 4.6, "Elliptical", null);
        galaxy.setId(1L);

        Mockito.when(universeService.generateGalaxy()).thenReturn(galaxy);

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
        StarSystem starSystem = new StarSystem("TestStarSystem", 4.6, null, null);
        starSystem.setId(1L);

        Mockito.when(universeService.generateStarSystem()).thenReturn(starSystem);

        mockMvc.perform(get("/star_system/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestStarSystem"))
                .andExpect(jsonPath("$.age").value(4.6))
                .andExpect(jsonPath("$.stars").value((Object) null))
                .andExpect(jsonPath("$.planets").value((Object) null))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGenerateRandomStar() throws Exception {
        Star star = new Star("TestStar", StarType.BLUE_GIANT, 1.989e30, 1.3927e6, 5778);
        star.setId(1L);

        Mockito.when(universeService.generateStar()).thenReturn(star);

        mockMvc.perform(get("/star/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestStar"))
                .andExpect(jsonPath("$.starType").value("BLUE_GIANT"))
                .andExpect(jsonPath("$.mass").value(1.989e30))
                .andExpect(jsonPath("$.diameter").value(1.3927e6))
                .andExpect(jsonPath("$.temperature").value(5778))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGenerateRandomPlanet() throws Exception {
        Planet planet = new Planet("TestPlanet", PlanetType.TERRESTRIAL, 1000, 1e20, null, false, null);
        planet.setId(1L);

        Mockito.when(universeService.generatePlanet()).thenReturn(planet);

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
        Moon moon = new Moon("TestMoon", 1000, 1e20);
        moon.setId(1L);

        Mockito.when(universeService.generateMoon()).thenReturn(moon);

        mockMvc.perform(get("/moon/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestMoon"))
                .andExpect(jsonPath("$.diameter").value(1000.0))
                .andExpect(jsonPath("$.mass").value(1e20))
                .andExpect(jsonPath("$.id").value(1));
    }
}
