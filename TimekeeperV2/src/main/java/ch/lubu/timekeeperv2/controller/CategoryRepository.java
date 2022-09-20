package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lukas Bühler
 * @version 2.0
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // find by id

}
