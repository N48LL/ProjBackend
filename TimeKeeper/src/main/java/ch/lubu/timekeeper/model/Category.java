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

    @Column(name = "cat_name", unique = true)
    private String catName;
    public String getCatName() {
        return catName;
    }
    public void setCatName(String catName) {
        this.catName = catName;
    }
}