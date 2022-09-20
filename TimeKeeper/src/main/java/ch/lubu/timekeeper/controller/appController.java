package ch.lubu.timekeeper.controller;

import ch.lubu.timekeeper.exception.TimeLoadException;
import ch.lubu.timekeeper.model.Category;
import ch.lubu.timekeeper.controller.TimeDto;
import ch.lubu.timekeeper.model.CategoryRepository;
import ch.lubu.timekeeper.model.Time;
import ch.lubu.timekeeper.model.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * This class is the controller for the time entity.
 * @author Lukas Bühler
 * @version 1.0
 */
@RestController
@RequestMapping("/app")
public class appController {
    @Autowired
    private TimeRepository timeRepository;

    // Show ALL times
    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Time>> getAllTimes() {
        Iterable<Time> times = timeRepository.findAll(); // this was null??? why?
        try {
            times = timeRepository.findAll();
        } catch (Exception ex) {
            throw new TimeLoadException();
        }
        return ResponseEntity.ok(times);
    }
    /*
    // Create new time
    @PostMapping(path = "/add")
    public Time saveTime(@RequestBody Time time) {
        return timeRepository.save(time);
    }
    */
    // todo: change this to categoryRepo
    @GetMapping(path = "/category/{id}")
    public ResponseEntity<Iterable<Time>> getTimesByCategory(@PathVariable Integer id) {
        Iterable<Time> times = timeRepository.findByCategoryId(id); // this was null???
        try {
            times = timeRepository.findByCategoryId(id);
        } catch (Exception ex) {
            throw new TimeLoadException();
        }
        return ResponseEntity.ok(times);
    }


    // Insert new time
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewTime(@RequestBody TimeDto newTime) throws ParseException {
        Time t = new Time();
        t.setAmount(newTime.getAmount());

        Category category = (Category) CategoryRepository.findByCategoryId(newTime.getCategory());
        /* Category category = (Category) CategoryRepository.findByCategoryId(newTime.getCategory()); */
        t.setCategory(category);
        t.setComment(newTime.getComment());
        t.setYear(Integer.valueOf(newTime.getYear()));
        t.setDate(newTime.getDay(), newTime.getYear(), newTime.getMonth());

        try {
            timeRepository.save(t);
        } catch (Exception ex) {
            throw new TimeLoadException();
        }
        return ResponseEntity.ok("Saved");
    }

}
