package ch.lubu.timekeeper.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lukas Bühler
 * @version 1.0
 * @see ch.lubu.timekeeper.controller.appController
 */

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}