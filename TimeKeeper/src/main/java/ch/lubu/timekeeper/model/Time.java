package ch.lubu.timekeeper.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
// sql table with indexing ;)
@Table(name = "time", indexes = {
        @Index(name = "idx_time_amount", columnList = "amount")
})
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Please enter a valid time")
    @NotEmpty
    @Column(name = "amount", nullable = false)
    private String amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "year_id", nullable = false)
    private Year year;

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

}