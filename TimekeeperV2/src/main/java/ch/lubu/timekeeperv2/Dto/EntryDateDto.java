package ch.lubu.timekeeperv2.Dto;

import ch.lubu.timekeeperv2.model.EntryDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link EntryDate} entity
 */
public class EntryDateDto implements Serializable {
    private Integer id;
    private String year;
    private String month;
    private String day;
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