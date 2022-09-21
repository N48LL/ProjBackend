package ch.lubu.timekeeperv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is the controller for the category entity.
 * @author Lukas Bühler
 * @version 2.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Show ALL categories
    @GetMapping(path = "/all")
    public Iterable<ch.lubu.timekeeperv2.model.Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
