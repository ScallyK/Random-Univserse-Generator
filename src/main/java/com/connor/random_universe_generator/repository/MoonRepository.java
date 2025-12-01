package com.connor.random_universe_generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connor.random_universe_generator.model.Moon;

public interface MoonRepository extends JpaRepository<Moon, Long> {}