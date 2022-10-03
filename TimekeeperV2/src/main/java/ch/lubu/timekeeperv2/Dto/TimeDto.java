package ch.lubu.timekeeperv2.Dto;

import ch.lubu.timekeeperv2.model.EntryDate;
import ch.lubu.timekeeperv2.model.Time;
import com.sun.xml.bind.v2.TODO;
import org.hibernate.mapping.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Time} entity
 */
public class TimeDto implements Serializable {
    private CategoryDto category;
    private java.sql.Time amount;
    private EntryDateDto entryDate;
    private Iterable<Time> time;


    public TimeDto(CategoryDto category, java.sql.Time amount, EntryDateDto entryDate, Iterable<Time> time) {
        this.category = category;
        this.amount = amount;
        this.entryDate = entryDate;
        this.time = time;
    }

    // TODO: Contue from here
    @NotNull(message = "Kategorie darf nicht leer sein.")
    public CategoryDto getCategory() {
        return category;
    }
    public TimeDto setCategory(CategoryDto category) {
        this.category = category;
        return this;
    }

    public java.sql.Time getAmount() {
        return amount;
    }
    public TimeDto setAmount(java.sql.Time amount) {
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

    public Iterable<Time> getTime() {
        return time;
    }

    public TimeDto setTime(Iterable<Time> time) {
        this.time = time;
        return this;
    }



}