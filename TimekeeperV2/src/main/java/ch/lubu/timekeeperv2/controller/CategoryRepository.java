package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Lukas Bühler
 * @version 2.0
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //get category by name returns category
    @Query("SELECT c FROM Category c WHERE c.category = ?1")
    Category findByName(String name);
}