package ch.lubu.timekeeper.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * This class represents the comment table in the database.
 * @author Lukas Bühler
 * @version 1.0
 *
 */

@Entity
@Table(name = "comment")
public class Comment {
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

    @Column(name = "text")
    private String text;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "date", unique = true, nullable = false)
    private Date date;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}