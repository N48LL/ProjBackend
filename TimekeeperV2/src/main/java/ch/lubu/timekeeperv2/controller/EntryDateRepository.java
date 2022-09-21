package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * @author Lukas Bühler
 * @version 2.0
 */
public interface EntryDateRepository extends JpaRepository<EntryDate, Integer> {
    @Query("select (count(e) > 0) from EntryDate e where e.date = ?1")
    boolean ifDateExists(Date date);

}
