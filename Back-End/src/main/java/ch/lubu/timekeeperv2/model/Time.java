package ch.lubu.timekeeperv2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class is the entity for the Time table in the database.
 * @author Lukas Bühler
 * @version 2.0
 * @link TimeDTO, TimeRepository, TimeController
 * @see Validation: {@link ch.lubu.timekeeperv2.Dto.TimeDto}
 */

@Entity
//TODO add unique constraint message
//@UniqueCategoryEntryDate(message = "category already selected for date")
@Table(name = "time", uniqueConstraints = {
        @UniqueConstraint(name = "uc_time_category_id", columnNames = {"category_id", "entry_date_id"})
})
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "amount", nullable = false)
    private java.sql.Time amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "entry_date_id", nullable = false)
    @JsonBackReference
    private EntryDate entryDate;

    public EntryDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(EntryDate entryDate) {
        this.entryDate = entryDate;
    }


    public java.sql.Time getAmount() {
        return amount;
    }
    public void setAmount(java.sql.Time amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}