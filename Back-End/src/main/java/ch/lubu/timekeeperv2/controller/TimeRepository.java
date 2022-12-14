package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.Category;
import ch.lubu.timekeeperv2.model.EntryDate;
import ch.lubu.timekeeperv2.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * This is the repository for the {@link Time} entity.
 * @author Lukas Bühler
 * TODO: DELETE unised Queries
 */
public interface TimeRepository extends JpaRepository<Time, Integer> {

    // shows all by year
    @Query("SELECT t FROM Time t WHERE YEAR(t.entryDate.date) = ?1")
    Iterable<Time> findByYear(Integer year);

    // shows all by year + month
    @Query("SELECT t FROM Time t WHERE YEAR(t.entryDate.date) = ?1 AND MONTH(t.entryDate.date) = ?2")
    Iterable<Time> findByMonth(Integer year, Integer month);

    // shows single day by year + month + day
    @Query("SELECT t FROM Time t WHERE YEAR(t.entryDate.date) = ?1 AND MONTH(t.entryDate.date) = ?2 AND DAY(t.entryDate.date) = ?3")
    Iterable<Time> findByDay(Integer year, Integer month, Integer day);

    // shows sum of amount by day - amount in form of hh:mm:ss
    @Query("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(t.amount))) FROM Time t WHERE YEAR(t.entryDate.date) = ?1 AND MONTH(t.entryDate.date) = ?2 AND DAY(t.entryDate.date) = ?3")
    java.sql.Time findSumByDay(Integer year, Integer month, Integer day);

    // shows all category, amount by day
    @Query("SELECT t.category, SEC_TO_TIME(SUM(TIME_TO_SEC(t.amount))) FROM Time t WHERE YEAR(t.entryDate.date) = ?1 AND MONTH(t.entryDate.date) = ?2 AND DAY(t.entryDate.date) = ?3 GROUP BY t.category")
    Iterable<Object[]> findSumByDayGroupByCategory(Integer year, Integer month, Integer day);

    // Select entry_date_id, category_id exists
    @Query("SELECT t FROM Time t WHERE t.entryDate = ?1 AND t.category = ?2")
    Optional<Time> findByEntryDateAndCategory(EntryDate entryDate, Category category);

    // delete all in timetable by entry_date_id
    @Modifying
    @Transactional
    @Query("DELETE FROM Time t WHERE entry_date_id = ?1")
    void deleteByEntryDateId(Long entryDate);


}