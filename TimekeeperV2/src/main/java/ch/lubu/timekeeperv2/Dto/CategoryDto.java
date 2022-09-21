package ch.lubu.timekeeperv2.Dto;

import ch.lubu.timekeeperv2.model.Category;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
public class CategoryDto implements Serializable {
    private Integer id;

    public CategoryDto() {
    }

    public CategoryDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public CategoryDto setId(Integer id) {
        this.id = id;
        return this;
    }

}