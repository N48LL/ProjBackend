package ch.lubu.timekeeper.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;

/**
 * This class represents the year table in the database.
 * @author Lukas Bühler
 * @version 1.0
 *
 */

@Entity
@Table(name = "year")
public class Year {

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

    @Digits (integer = 4, fraction = 0, message = "Please enter a valid year")
    @Column(name = "year", nullable = false, unique = true)
    private int year;
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}