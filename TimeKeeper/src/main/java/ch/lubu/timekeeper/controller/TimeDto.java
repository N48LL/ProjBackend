package ch.lubu.timekeeper.controller;

import ch.lubu.timekeeper.model.Comment;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ch.lubu.timekeeper.model.Time} entity
 */
public class TimeDto implements Serializable {

    @NotEmpty
    private final String amount;
    @NotEmpty
    private final String year;
    private final int category;
    private final Comment comment;
    @NotEmpty
    private final String month;
    @NotEmpty
    private final String day;

    public TimeDto(String amount, String year, int category, Comment comment, String month, String day) {
        this.amount = amount;
        this.year = year;
        this.category = category;
        this.comment = comment;
        this.month = month;
        this.day = day;
    }


    public String getAmount() {
        return amount;
    }

    public String getYear() {
        return year;
    }

    public int getCategory() {
        return category;
    }

    public Comment getComment() {
        return comment;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeDto entity = (TimeDto) o;
        return  Objects.equals(this.amount, entity.amount) &&
                Objects.equals(this.year, entity.year) &&
                Objects.equals(this.category, entity.category) &&
                Objects.equals(this.comment, entity.comment) &&
                Objects.equals(this.month, entity.month) &&
                Objects.equals(this.day, entity.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, year, category, comment, year, month, day);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "amount = " + amount + ", " +
                "year = " + year + ", " +
                "category = " + category + ", " +
                "comment = " + comment + ", " +
                "month = " + month + ", " +
                "day = " + day + ")";
    }
     */
}