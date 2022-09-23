package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * @author Lukas Bühler
 */
public interface EntryDateRepository extends JpaRepository<EntryDate, Integer> {

        @Query("SELECT d FROM EntryDate d WHERE YEAR(d.date) = ?1")
        Iterable<EntryDate> findByYear(Integer year);
}
