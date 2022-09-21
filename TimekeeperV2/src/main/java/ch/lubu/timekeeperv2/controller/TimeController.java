package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.Dto.TimeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * This class is the controller for the time entity including FK category.
 * @author Lukas Bühler
 * @version 2.0
 */
@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    // Show ALL times
    @GetMapping(path = "/all")
    public Iterable<ch.lubu.timekeeperv2.model.Time> getAllTimes() {
        return timeRepository.findAll();
    }

    // create new time from TimeDto.java and find category by id
    // fucking magic
    @PostMapping(path = "/add")
    public ch.lubu.timekeeperv2.model.Time saveTime(@RequestBody TimeDto newTime) {
        ch.lubu.timekeeperv2.model.Time time = new ch.lubu.timekeeperv2.model.Time();
        ch.lubu.timekeeperv2.model.Category category = categoryRepository.findById(newTime.getCategory().getId()).get();

        time.setCategory(category);
        time.setAmount(newTime.getAmount());
        return timeRepository.save(time);
    }

    // update time by id
    @PutMapping(path = "/update/{id}")
    public ch.lubu.timekeeperv2.model.Time updateTime(@PathVariable Integer id, @RequestBody TimeDto newTime) {
        ch.lubu.timekeeperv2.model.Time time = timeRepository.findById(id).get();
        ch.lubu.timekeeperv2.model.Category category = categoryRepository.findById(newTime.getCategory().getId()).get();

        time.setCategory(category);
        time.setAmount(newTime.getAmount());
        return timeRepository.save(time);
    }

    // delete Time entry by time id
    @DeleteMapping(path = "/delete/{id}")
    public HttpStatus deleteTime(@PathVariable int id) {
        this.timeRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
