package ch.lubu.timekeeper.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;

/**
 * This class represents the time table in the database.
 * @author Lukas Bühler
 * @version 1.0
 *
 */

@Entity
@Table(name = "time", indexes = {
        @Index(name = "idx_time_amount", columnList = "amount")
        /*
         * indexierung der spalte amount
         */
})
public class Time {

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

    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Please enter a valid time")
    @NotEmpty
    @Column(name = "amount", nullable = false)
    private String amount;
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Column(name = "date", nullable = false)
    @CreationTimestamp
    private Date date;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * FK Year
     */
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "year_id", nullable = false)
    private Year year;
    public Year getYear() {
        return year;
    }
    public void setYear(Year year) {
        this.year = year;
    }

    /**
     * FK Category
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "category_id")
    private Category category;
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    /**
     * FK Comment - Unique
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "comment_id")
    private Comment comment;
    public Comment getComment() {
        return comment;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }

}