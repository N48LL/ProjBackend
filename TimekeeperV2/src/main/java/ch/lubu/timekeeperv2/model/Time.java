package ch.lubu.timekeeperv2.model;

import javax.persistence.*;
/**
 * This class is the entity for the Time table in the database.
 * @author Lukas Bühler
 * @version 2.0
 */
@Entity
@Table(name = "time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "amount", nullable = false)
    private String amount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "entry_date_id", nullable = false)
    private EntryDate entryDate;

    public EntryDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(EntryDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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