package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This class is the repository for the {@link Category} entity.
 * @author Lukas Bühler
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * This method returns a category by name.
     * @param category name of the category
     * @return category
     * @link Category.java -> getCategory
     */
    @Query("SELECT c FROM Category c WHERE c.category = ?1")
    Category findByName(String name);
}