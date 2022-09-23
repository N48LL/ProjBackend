package ch.lubu.timekeeperv2.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is the entity for the category table in the database.
 * @author Lukas Bühler
 * @version 2.0
 */

@Entity
@Table(name = "date")
public class EntryDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false, unique = true)
    private java.util.Date date;

    @Column(name = "comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public java.util.Date getDate() {
        return date;
    }
    public void setDate(String year, String month, String day) throws ParseException {
        String dateString = day+"."+month+"."+year;
        this.date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
    }

    public String getYear() {
        return new SimpleDateFormat("yyyy").format(date);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}