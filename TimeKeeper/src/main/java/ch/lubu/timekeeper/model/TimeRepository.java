package ch.lubu.timekeeper.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Lukas Bühler
 * @version 1.0
 */

public interface TimeRepository extends JpaRepository<Time, Integer> {

    List<Time> findByCategoryId(Integer id);
    List<Time> findByYear(Integer year);

}