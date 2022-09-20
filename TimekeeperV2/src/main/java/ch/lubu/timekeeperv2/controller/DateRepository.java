package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Lukas Bühler
 * @version 2.0
 */
public interface DateRepository extends JpaRepository<Date, Integer> {

}
