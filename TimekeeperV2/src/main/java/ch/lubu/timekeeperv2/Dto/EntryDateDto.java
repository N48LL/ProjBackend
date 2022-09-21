package ch.lubu.timekeeperv2.Dto;

import ch.lubu.timekeeperv2.model.EntryDate;

import java.io.Serializable;

/**
 * A DTO for the {@link EntryDate} entity
 */
public class EntryDateDto implements Serializable {
    private Integer id;

    public EntryDateDto() {
    }

    public EntryDateDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public EntryDateDto setId(Integer id) {
        this.id = id;
        return this;
    }
}