package ch.lubu.timekeeper.controller;

import ch.lubu.timekeeper.exception.CategoryCouldNotBeSavedException;
import ch.lubu.timekeeper.exception.CategoryLoadException;
import ch.lubu.timekeeper.model.Category;
import ch.lubu.timekeeper.model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is used to create a new category and view all categories.
 * @author Lukas Bühler
 * @version 1.0
 * Todo: rewrite exceptions
 * Todo: Test PostMapping
 */

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path = "")
    public ResponseEntity<String> addNewCategory(@RequestParam String category) {

        Category c = new Category();
        c.setCategory(category);

        try {
            categoryRepository.save(c);
        } catch (Exception ex) {
            throw new CategoryCouldNotBeSavedException(category);
        }

        return ResponseEntity.ok("Saved");
    }

    @GetMapping(path = "")
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