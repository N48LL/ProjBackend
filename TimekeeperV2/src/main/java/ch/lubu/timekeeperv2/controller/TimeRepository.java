package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Lukas Bühler
 * @version 2.0
 */
public interface TimeRepository extends JpaRepository<Time, Integer> {

    // show all by year
    @Query("SELECT t FROM Time t WHERE YEAR(t.entryDate.date) = ?1")
    Iterable<Time> findByYear(Integer year);
    // show all by year + month
    @Query("SELECT t FROM Time t WHERE YEAR(t.entryDate.date) = ?1 AND MONTH(t.entryDate.date) = ?2")
    Iterable<Time> findByMonth(Integer year, Integer month);
    // show single day by year + month + day
    @Query("SELECT t FROM Time t WHERE YEAR(t.entryDate.date) = ?1 AND MONTH(t.entryDate.date) = ?2 AND DAY(t.entryDate.date) = ?3")
    Iterable<Time> findByDay(Integer year, Integer month, Integer day);

}
