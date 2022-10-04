package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.Dto.EntryDateDto;
import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

/**
 * This is the repository for the {@link EntryDate} entity.
 * @author Lukas Bühler
 */
public interface EntryDateRepository extends JpaRepository<EntryDate, Integer> {

        // show all by year
        @Query("SELECT d FROM EntryDate d WHERE YEAR(d.date) = ?1")
        Iterable<EntryDate> findByYear(Integer year);

        /**
         * This method fetches all days from a given month and year.
         * @param year
         * @param month
         * @return Iterable<EntryDate>
         * @link EntryDateController.java -> getDatesByMonth()
         */
        @Query("SELECT d FROM EntryDate d WHERE YEAR(d.date) = ?1 AND MONTH(d.date) = ?2")
        Iterable<EntryDate> findByMonth(Integer year, Integer month);
        // show single day by year + month + day
        @Query("SELECT d FROM EntryDate d WHERE YEAR(d.date) = ?1 AND MONTH(d.date) = ?2 AND DAY(d.date) = ?3")
        EntryDate findByDay(Integer year, Integer month, Integer day);

        /**
         * This method searches all Data and returns a list of all available years.
         * @link EntryDateController.java -> getYears()
         * @return List of all available years as Integer
         */
        @Query("SELECT DISTINCT YEAR(d.date) FROM EntryDate d")
        Iterable<Integer> findDistrictYears();

        /**
         * This method searches all Data by year and returns a list of all available months.
         * @param year
         * @link EntryDateController.java -> getMonthsByYear()
         * @return List of all available months as Integer
         */
        @Query("SELECT DISTINCT MONTH(d.date) FROM EntryDate d WHERE YEAR(d.date) = ?1")
        Iterable<Integer> findDistrictMonths(Integer year);


        /**
         * This method is used to fetch the sum of all times by day.
         * @param year
         * @param month
         * @link EntryDateController.java -> getSumByMonth()
         * @return total sum of all times by day as java.sql.Time
         */
        @Query("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(t.amount))) FROM Time t JOIN t.entryDate d WHERE YEAR(d.date) = ?1 AND MONTH(d.date) = ?2 GROUP BY d.date")
        Iterable<java.sql.Time> findSumByMonth(Integer year, Integer month);

        /**
         * This method is used to fetch Date ID by year month and day.
         * @param year
         * @param month
         * @param day
         * @link EntryDateController.java -> getSumByDay()
         * @return Date ID as Integer
         */
        @Query("SELECT d.id FROM EntryDate d WHERE d.date = ?1")
        Optional<Integer> findIdByDate(Date date);

}
