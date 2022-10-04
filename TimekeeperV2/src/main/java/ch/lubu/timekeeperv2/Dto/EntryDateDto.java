package ch.lubu.timekeeperv2.Dto;

import ch.lubu.timekeeperv2.model.EntryDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link EntryDate} entity
 */
public class EntryDateDto implements Serializable {
    private Integer id;
    @Size(max = 4, min = 4, message = "Jahr muss 4-stellig sein.")
    @Max(value = 2099, message = "Das jahr muss in diesem Jahrhundert liegen.")
    @Min(value = 2000, message = "Das jahr muss in diesem Jahrhundert liegen.")
    @NotEmpty(message = "Jahr darf nicht leer sein.")
    private String year;
    @Min(value = 1, message = "Monat muss grösser als 0 sein.")
    @Max(value = 12, message = "Monat darf nicht grösser als 12 sein.")
    @NotEmpty(message = "Monat darf nicht leer sein.")
    private String month;
    @Min(value = 1, message = "Tag muss grösser als 0 sein.")
    @Max(value = 31, message = "Tag darf nicht grösser als 31 sein.")
    @NotEmpty(message = "Tag darf nicht leer sein.")
    private String day;
    @Size(max = 1024, message = "Kommentar darf nicht länger als 1024 Zeichen sein.")
    private String comment;
    private List<TimeDto> time = new ArrayList<>();


    public EntryDateDto(String year, String month, String day, String comment, List<TimeDto> time) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.comment = comment;
        this.time = time;
    }

    public EntryDateDto(Integer id) {
        this.id = id;
    }

    public EntryDateDto() {

    }

    public Integer getId() {
        return id;
    }

    public EntryDateDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getComment() {
        return comment;
    }
    public String setComment(String comment) {
        this.comment = comment;
        return comment;
    }

    public String getYear() {
        return year;
    }
    public EntryDateDto setYear(String year) {
        this.year = year;
        return this;
    }

    public String getMonth() {
        return month;
    }
    public EntryDateDto setMonth(String month) {
        this.month = month;
        return this;
    }

    public String getDay() {
        return day;
    }
    public EntryDateDto setDay(String day) {
        this.day = day;
        return this;
    }

    public List<TimeDto> getTime() {
        return time;
    }
    public EntryDateDto setTime(List<TimeDto> time) {
        this.time = time;
        return this;
    }

}