package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.Dto.TimeDto;
import ch.lubu.timekeeperv2.exception.TimeCouldNotBeSavedException;
import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the controller for the time entity including FK category.
 * @author Lukas Bühler
 */
@RestController
@CrossOrigin
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntryDateRepository entryDateRepository;

    // Show ALL times
    @GetMapping(path = "/all")
    public Iterable<ch.lubu.timekeeperv2.model.Time> getAllTimes() {
        return timeRepository.findAll();
    }

    // Show all by year
    @GetMapping(path = "/{year}")
    public Iterable<ch.lubu.timekeeperv2.model.Time> getTimesByYear(@PathVariable Integer year) {
        return timeRepository.findByYear(year);
    }

    // show all by year + month
    @GetMapping(path = "/{year}/{month}")
    public Iterable<ch.lubu.timekeeperv2.model.Time> getTimesByMonth(@PathVariable Integer year, @PathVariable Integer month) {
        return timeRepository.findByMonth(year, month);
    }

    // show single day by year + month + day
    @GetMapping(path = "/{year}/{month}/{day}")
    public Iterable<ch.lubu.timekeeperv2.model.Time> getTimesByDay(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return timeRepository.findByDay(year, month, day);
    }

    // create new time from TimeDto.java and find category by id
    @PostMapping(path = "/add")
    public ch.lubu.timekeeperv2.model.Time saveTime(@RequestBody TimeDto newTime) {
        ch.lubu.timekeeperv2.model.Time time = new ch.lubu.timekeeperv2.model.Time();
        ch.lubu.timekeeperv2.model.Category category = categoryRepository.findById(newTime.getCategory().getId()).get();
        EntryDate entryDate = entryDateRepository.findById(newTime.getEntryDate().getId()).get();

        time.setEntryDate(entryDate);
        time.setCategory(category);
        time.setAmount(newTime.getAmount());

        try {
            return timeRepository.save(time);
        } catch (Exception ex) {
            throw new TimeCouldNotBeSavedException(time);
        }
    }

    // update time by id
    @PutMapping(path = "/update/{id}")
    public ch.lubu.timekeeperv2.model.Time updateTime(@PathVariable Integer id, @RequestBody TimeDto newTime) {
        ch.lubu.timekeeperv2.model.Time time = timeRepository.findById(id).get();
        ch.lubu.timekeeperv2.model.Category category = categoryRepository.findById(newTime.getCategory().getId()).get();
        EntryDate entryDate = entryDateRepository.findById(newTime.getEntryDate().getId()).get();

        time.setEntryDate(entryDate);
        time.setCategory(category);
        time.setAmount(newTime.getAmount());
        try {
            return timeRepository.save(time);
        } catch (Exception ex) {
            throw new TimeCouldNotBeSavedException(time);
        }
    }

    // delete Time entry by time id
    @DeleteMapping(path = "/delete/{id}")
    public HttpStatus deleteTime(@PathVariable int id) {
        this.timeRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
