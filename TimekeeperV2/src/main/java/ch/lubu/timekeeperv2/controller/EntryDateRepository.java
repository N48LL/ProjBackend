package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

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

}
