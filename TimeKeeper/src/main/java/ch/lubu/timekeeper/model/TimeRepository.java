package ch.lubu.timekeeper.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author Lukas Bühler
 * @version 1.0
 */

public interface TimeRepository extends JpaRepository<Time, Integer> {

    // todo: useless?
    List<Time> findByAmount(String amount);

    List<Time> findByYear_Year(int year);
    /**
     * alle eintraege nach year
     * @param year
     * @return ResponseEntity.ok(times)
     * @see Time
     * @see ch.lubu.timekeeper.controller.appController
     */

}