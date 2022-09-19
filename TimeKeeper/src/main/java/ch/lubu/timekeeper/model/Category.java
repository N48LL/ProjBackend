package ch.lubu.timekeeper.model;

import javax.persistence.*;

/**
 * this class represents the category table in the database.
 * @author Lukas Bühler
 * @version 1.0
 */

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "category", nullable = false, unique = true)
    private String category;
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}