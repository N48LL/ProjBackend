package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.EntryDate;
import ch.lubu.timekeeperv2.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Lukas Bühler
 */
public interface EntryDateRepository extends JpaRepository<EntryDate, Integer> {

        // show all by year
        @Query("SELECT d FROM EntryDate d WHERE YEAR(d.date) = ?1")
        Iterable<EntryDate> findByYear(Integer year);
        // show all by year + month
        @Query("SELECT d FROM EntryDate d WHERE YEAR(d.date) = ?1 AND MONTH(d.date) = ?2")
        Iterable<EntryDate> findByMonth(Integer year, Integer month);
        // show single day by year + month + day
        @Query("SELECT d FROM EntryDate d WHERE YEAR(d.date) = ?1 AND MONTH(d.date) = ?2 AND DAY(d.date) = ?3")
        EntryDate findByDay(Integer year, Integer month, Integer day);

        // show all years limited to no dublicates
        @Query("SELECT DISTINCT YEAR(d.date) FROM EntryDate d")
        Iterable<Integer> findDistrictYears();

        // show all months by year limited to no dublicates
        @Query("SELECT DISTINCT MONTH(d.date) FROM EntryDate d WHERE YEAR(d.date) = ?1")
        Iterable<Integer> findDistrictMonths(Integer year);

        // shows sum of amount by day for each day in month
        @Query("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(t.amount))) FROM Time t JOIN t.entryDate d WHERE YEAR(d.date) = ?1 AND MONTH(d.date) = ?2 GROUP BY d.date")
        Iterable<java.sql.Time> findSumByMonth(Integer year, Integer month);

        //find id by date
        @Query("SELECT d.id FROM EntryDate d WHERE d.date = ?1")
        Optional<Integer> findIdByDate(Date date);
}
