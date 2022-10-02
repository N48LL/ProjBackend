package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the controller for the {@link Category} entity.
 * @author Lukas Bühler
 * @version 2.0
 * @see CategoryRepository
 */
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Show ALL categories
    /**
     * This method shows all categories.
     * @return Iterable of all categories
     * @link CategoryRepository.java -> findAll
     */
    @GetMapping(path = "/all")
    public Iterable<ch.lubu.timekeeperv2.model.Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}