package ch.lubu.timekeeperv2.model;

import javax.persistence.*;
/**
 * This class is the entity for the category table in the database.
 * @author Lukas Bühler
 * @version 2.0
 * @link CategoryRepository, CategoryController, CategoryDTO
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "category", nullable = false, unique = true)
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category get() {
        return this;
    }
}