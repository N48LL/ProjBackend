package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.exception.CategoryLoadException;
import ch.lubu.timekeeperv2.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the controller for the {@link Category} entity.
 *
 * @author Lukas Bühler
 * @version 2.0
 * @see CategoryRepository
 *
 */

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * This method shows all categories.
     * @return Iterable of all categories
     * @link CategoryRepository.java -> findAll
     * @throws CategoryLoadException
     */
    @GetMapping(path = "/all")
     public ResponseEntity<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = null;
        try {
            categories = categoryRepository.findAll();
        } catch (Exception ex) {
            throw new CategoryLoadException();
        }
        return ResponseEntity.ok(categories);
    }
}