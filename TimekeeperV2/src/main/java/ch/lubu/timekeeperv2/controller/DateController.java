package ch.lubu.timekeeperv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the controller for the date entity.
 * @author Lukas Bühler
 * @version 2.0
 */

@RestController
@RequestMapping("/date")
public class DateController {

    @Autowired
    private DateRepository dateRepository;

    // Show ALL dates
    @GetMapping(path = "/all")
    public Iterable<ch.lubu.timekeeperv2.model.Date> getAllDates() {
        return dateRepository.findAll();
    }

   // create new date
    @PostMapping(path = "/add")
    public ch.lubu.timekeeperv2.model.Date saveDate(@RequestBody ch.lubu.timekeeperv2.model.Date date) {
        return dateRepository.save(date);
    }

}
