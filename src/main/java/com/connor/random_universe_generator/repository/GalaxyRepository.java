package com.connor.random_universe_generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connor.random_universe_generator.model.SimpleGalaxy;

public interface GalaxyRepository extends JpaRepository<SimpleGalaxy, Long> {}
