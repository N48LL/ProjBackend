package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.Dto.TimeDto;
import ch.lubu.timekeeperv2.exception.TimeCatEntrydateDublicateException;
import ch.lubu.timekeeperv2.exception.TimeCouldNotBeSavedException;
import ch.lubu.timekeeperv2.model.EntryDate;
import ch.lubu.timekeeperv2.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class is the controller for the {@link Time} entity.
 * @author Lukas Bühler
 * @see TimeRepository
 * @see Validation: {@link ch.lubu.timekeeperv2.Dto.TimeDto}
 */

@CrossOrigin
@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntryDateRepository entryDateRepository;

    /**
     * This method is used to get all Time entries.
     * @return Iterable of all Time entries.
     * @see Time
     */
    @GetMapping(path = "/all")
    public Iterable<ch.lubu.timekeeperv2.model.Time> getAllTimes() {
        return timeRepository.findAll();
    }

    /**
     * This method adds a new time entry to a date with amount and category.
     * @link TimeRepository.java -> findById
     * @param category, amount, entryDate
     * @return List of Time
     * @throws TimeCouldNotBeSavedException if the time could not be saved
     * @throws TimeCatEntrydateDublicateException if the time, category or entrydate already exists
     * @link TimeRepository.java -> save
     * @link CategoryRepository.java -> findById
     * @link EntryDateRepository.java -> findById
     */
    @PostMapping(path = "/add")
    public ch.lubu.timekeeperv2.model.Time saveTime(@Valid @RequestBody TimeDto Dto) throws TimeCouldNotBeSavedException {
        ch.lubu.timekeeperv2.model.Time time = new ch.lubu.timekeeperv2.model.Time();
        ch.lubu.timekeeperv2.model.Category category = categoryRepository.findById(Dto.getCategory().getId()).get();
        EntryDate entryDate = entryDateRepository.findById(Dto.getEntryDate().getId()).get();

        time.setEntryDate(entryDate);
        time.setCategory(category);
        //setAmount time to seconds
        time.setAmount(Dto.getAmount());
        // check if entryDate id and category id already exist together
        if (timeRepository.findByEntryDateAndCategory(entryDate, category).isPresent()) {
            throw new TimeCatEntrydateDublicateException();
        }
            try {
                timeRepository.save(time);
            } catch (Exception ex) {
                throw new TimeCouldNotBeSavedException(time);
            }
            return time;
    }

    /**
     * This method deletes all time entry by fk id of entryDate.
     *
     * @param entry_date_id
     * @return HttpStatus
     * @link TimeRepository.java -> deleteByEntryDateId
     * @throws printStackTrace Bad_Request
     */
    @DeleteMapping(path = "/{entry_date_id}/delete")
    public HttpStatus deleteTimeByEntryDateId(@PathVariable Long entry_date_id) {
        try {
            timeRepository.deleteByEntryDateId(entry_date_id);
            return HttpStatus.ACCEPTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }

    // create new time from TimeDto.java and find catgegory by name set category by id
    @PostMapping(path = "/add/{categoryName}")
    public ch.lubu.timekeeperv2.model.Time saveTime(@Valid @RequestBody TimeDto newTime, @PathVariable String categoryName) throws TimeCouldNotBeSavedException {
        ch.lubu.timekeeperv2.model.Time time = new ch.lubu.timekeeperv2.model.Time();
        ch.lubu.timekeeperv2.model.Category category = categoryRepository.findByName(categoryName);
        EntryDate entryDate = entryDateRepository.findById(newTime.getEntryDate().getId()).get();

        time.setEntryDate(entryDate);
        time.setCategory(category.get());
        time.setAmount(newTime.getAmount());

        try {
            return timeRepository.save(time);
        } catch (Exception ex) {
            throw new TimeCouldNotBeSavedException(time);
        }
    }

    // show sum of amount by day - amount in form of hh:mm:ss
    @GetMapping(path = "/{year}/{month}/{day}/sum")
    public java.sql.Time getSumByDay(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return timeRepository.findSumByDay(year, month, day);
    }

    // shows all category, amount by day
    @GetMapping(path = "/{year}/{month}/{day}/daysum")
    public Iterable<Object[]> getSumByDayWithCategory(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return timeRepository.findSumByDayGroupByCategory(year, month, day);
    }


    // update time by id
    @PutMapping(path = "/update/{id}")
    public ch.lubu.timekeeperv2.model.Time updateTime(@PathVariable Integer id, @Valid @RequestBody TimeDto newTime) throws TimeCouldNotBeSavedException {
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

    /**
     * This method deletes all time entry by id of EntryDate.
     * @param id
     */
    @DeleteMapping(path = "/delete/{id}")
    public HttpStatus deleteTime(@PathVariable int id) {
        try {
            timeRepository.deleteById(id);
            return HttpStatus.ACCEPTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }
}
