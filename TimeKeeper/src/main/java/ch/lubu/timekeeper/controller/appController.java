package ch.lubu.timekeeper.controller;

import ch.lubu.timekeeper.model.Time;
import ch.lubu.timekeeper.model.TimeRepository;
import ch.lubu.timekeeper.model.Year;
import ch.lubu.timekeeper.model.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @Autowired
    private YearRepository yearRepository;

    @GetMapping("/{year}")
    public ResponseEntity<?> getAmountByYear(@PathVariable("year") int year) {
        /**
         * alle eintraege nach year
         * @param year
         * @return ResponseEntity.ok(times)
         * @see TimeRepository
         * @see Time
         */
        List<Time> times = timeRepository.findByYear_Year(year);
        return ResponseEntity.ok(times);
    }

    // add new time entry with year and amount
    //todo: bug: this changes year to 0
    @PostMapping(path = "/add/{year}/{amount}")
    public ResponseEntity<?> addNewTime(@PathVariable("year") int year, @PathVariable("amount") String amount) {
        Optional<Year> yearOptional = yearRepository.findById(year);
        if (yearOptional.isPresent()) {
            Time time = new Time();
            time.setAmount(amount);
            time.setYear(yearOptional.get());
            timeRepository.save(time);
            return ResponseEntity.ok(time);
        } else {
            return ResponseEntity.badRequest().body("Year not found");
        }
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAll() {
        /**
         * alle time eintraege
         * @return ResponseEntity.ok(timeRepository.findAll());
         * @see TimeRepository
         * @see Time
         */
        return ResponseEntity.ok(timeRepository.findAll());
    }

    @GetMapping(path = "/allYears")
    public ResponseEntity<?> getAllYears() {
        /**
         * show all years
         * @return ResponseEntity.ok(yearRepository.findAll());
         * @see YearRepository
         * @see Year
         */
        return ResponseEntity.ok(yearRepository.findAll());
    }


}
