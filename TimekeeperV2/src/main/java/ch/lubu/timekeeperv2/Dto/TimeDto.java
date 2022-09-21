package ch.lubu.timekeeperv2.Dto;

import ch.lubu.timekeeperv2.model.EntryDate;
import ch.lubu.timekeeperv2.model.Time;

import java.io.Serializable;

/**
 * A DTO for the {@link Time} entity
 */
public class TimeDto implements Serializable {
    private CategoryDto category;
    private String amount;
    private EntryDateDto entryDate;

    public TimeDto() {
    }

    public TimeDto(CategoryDto category, String amount, EntryDateDto entryDate) {
        this.category = category;
        this.amount = amount;
        this.entryDate = entryDate;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public TimeDto setCategory(CategoryDto category) {
        this.category = category;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public TimeDto setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public EntryDateDto getEntryDate() {
        return entryDate;
    }
    public TimeDto setEntryDate(EntryDateDto entryDate) {
        this.entryDate = entryDate;
        return this;
    }

}