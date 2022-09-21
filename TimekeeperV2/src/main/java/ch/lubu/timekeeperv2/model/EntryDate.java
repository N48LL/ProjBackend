package ch.lubu.timekeeperv2.model;

import javax.persistence.*;
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
    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}