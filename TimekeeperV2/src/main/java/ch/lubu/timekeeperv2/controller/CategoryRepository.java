package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Lukas Bühler
 * @version 2.0
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
