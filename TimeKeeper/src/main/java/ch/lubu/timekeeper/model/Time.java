package ch.lubu.timekeeper.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents the time table in the database.
 * @author Lukas Bühler
 * @version 1.0
 *
 */

@Entity
@Table(name = "time", indexes = {
        @Index(name = "idx_time_amount", columnList = "amount")
})
@NamedQueries({
        @NamedQuery(name = "catById", query = "select t from Time t where t.category.id = :id")
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

    /**
     * Year
     */
    @NotEmpty
    @Column(name = "year", nullable = false, unique = true)
    private Integer year;
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * FK Category
     */
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
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
    /**
     * FK Date
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    public Date getDate() {
        return date;
    }
    public void setDate(String year, String month, String day) throws ParseException {
        String dateString = day+"."+month+"."+year;
        this.date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
    }

}