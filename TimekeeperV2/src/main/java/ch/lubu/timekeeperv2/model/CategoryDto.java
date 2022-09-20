package ch.lubu.timekeeperv2.model;

import java.io.Serializable;
import java.util.Objects;

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