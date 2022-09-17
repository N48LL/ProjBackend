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
 * @author Lukas Bühler
 * @version 1.0
 * @// TODO: 17.09.2022 add javadoc
 */
@RestController
@RequestMapping("/app")
public class appController {

    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private YearRepository yearRepository;

    // alle eintraege nach year
    @GetMapping("/{year}")
    public ResponseEntity<?> getAmountByYear(@PathVariable("year") int year) {
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





    // show all time entries
    @GetMapping(path = "/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(timeRepository.findAll());
    }

    // show all years
    @GetMapping(path = "/allYears")
    public ResponseEntity<?> getAllYears() {
        return ResponseEntity.ok(yearRepository.findAll());
    }

}
