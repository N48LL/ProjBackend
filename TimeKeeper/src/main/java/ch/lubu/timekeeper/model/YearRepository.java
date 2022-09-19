package ch.lubu.timekeeper.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lukas Bühler
 * @version 1.0
 * @see ch.lubu.timekeeper.controller.appController
 */

public interface YearRepository extends JpaRepository<Year, Integer> {
}