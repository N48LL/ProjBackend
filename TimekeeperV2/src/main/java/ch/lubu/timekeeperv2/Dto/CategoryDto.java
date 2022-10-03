package ch.lubu.timekeeperv2.Dto;

import ch.lubu.timekeeperv2.model.Category;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
public class CategoryDto implements Serializable {
    private Integer id;
    private String name;

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

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

}