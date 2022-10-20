package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.Dto.EntryDateDto;
import ch.lubu.timekeeperv2.exception.DateCouldNotBeSavedException;
import ch.lubu.timekeeperv2.exception.EntryDateLoadException;
import ch.lubu.timekeeperv2.exception.EntryDateNotFoundException;
import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * This class is the controller for the {@link EntryDate} entity.
 * @author Lukas Bühler
 * @see EntryDateRepository
 */

@CrossOrigin
@RestController
@RequestMapping("/date")
public class EntryDateController {

    @Autowired
    private EntryDateRepository entryDateRepository;

    /**
     * This method shows everything
     * @return Iterable of all dates
     * @link EntryDateRepository.java -> findAll
     */
    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<EntryDate>> getAllDates() {
        Iterable<EntryDate> dates = null;
        try {
            dates = entryDateRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(dates);
    }

    // show all by year
    // todo: delete this and run tests
    @GetMapping(path = "/{year}")
    public Iterable<EntryDate> getDatesByYear(@PathVariable Integer year) {
        return entryDateRepository.findByYear(year);
    }

    /**
     * This method fetches all days from a given month and year.
     * @link EntryDateRepository.java -> findByMonth
     * @param year
     * @param month
     * @return List of EntryDate
     * @throws EntryDateLoadException
     */
    @GetMapping(path = "/{year}/{month}")
    public Iterable<EntryDate> getDatesByMonth(@PathVariable Integer year, @PathVariable Integer month) {
        if (month > 12 || month < 1 || year < 1900 || year > 2999) {
            throw new EntryDateNotFoundException(year, month);
        }
        try {
            return entryDateRepository.findByMonth(year, month);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntryDateNotFoundException(year, month);
        }
    }

    /**
     * This method fetch all Data by year and returns a list of all avalable years.
     * @link EntryDateRepository.java -> findDistriktYears()
     * @return List of all available years
     * @throws EntryDateNotFoundException
     */
    @GetMapping(path = "/years")
    public Iterable<Integer> getYears() {
        try {
            return entryDateRepository.findDistrictYears();
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntryDateLoadException();
        }
    }

    /**
     * This method is used to fetch a list of months by year.
     * @link EntryDateRepository.java -> findByMonth
     * @return List of months
     * @param year, month
     * @throws EntryDateNotFoundException
     */
    @GetMapping(path = "/{year}/months")
    public Iterable<Integer> getMonthsByYear(@PathVariable Integer year) {
        if (year < 200 || year > 2100) {
            throw new EntryDateNotFoundException(year);
        }
        try {
            return entryDateRepository.findDistrictMonths(year);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EntryDateLoadException();
        }
    }


    /**
     * This method is used to fetch Date ID by concat year, month and day.
     *
     * @param Year, Month, Day, Comment
     * @return ID of the Date
     * @link EntryDateRepository.java -> save
     */
    @GetMapping(path = "/{year}/{month}/{day}/id")
    public EntryDate getIdByDate(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day){
        //if not found, return http status 404
        try {
            if (entryDateRepository.findByDay(year, month, day) == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date not found");
            } else {
                return entryDateRepository.findByDay(year, month, day);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return  exception for HttpStatus.BAD_REQUEST;
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date not found", e);
        }
    }

    /**
     * This method is used to add a new date with comment.
     * @param Year, Month, Day, Comment
     * @link EntryDateRepository.java -> save
     * @throws ParseException
     */
    @PostMapping(path = "/add")
    public EntryDate saveDate(@Valid @RequestBody EntryDateDto Dto) throws ParseException {
        EntryDate t = new EntryDate();

        t.setDate(Dto.getYear(), Dto.getMonth(), Dto.getDay());
        t.setComment(Dto.getComment());
        try {
            entryDateRepository.save(t);
        } catch (Exception ex) {
            throw new DateCouldNotBeSavedException(t);
        }
        return t;
    }

    /**
     * This method is used to update a date's comment.
     * @param id
     * @param entryDate
     * @link EntryDateRepository.java
     * @throws ParseException
     * @throws DateCouldNotBeSavedException
     */
    @PutMapping(path = "/update/{id}")
    public EntryDate updateComment(@PathVariable int id, @RequestBody EntryDateDto Dto) {
        Optional<EntryDate> t = entryDateRepository.findById(id);
        if (t.isPresent()) {
            t.get().setComment(Dto.getComment());
            entryDateRepository.save(t.get());
            return t.get();
        } else {
            throw new EntryDateNotFoundException(id);
        }
    }

    /**
     * This method deletes a date by id.
     * @param id
     * @return HttpStatus
     * @link EntryDateRepository.java
     */
    @DeleteMapping(path = "/delete/{id}")
    public HttpStatus deleteDate(@PathVariable int id) {
        try {
            entryDateRepository.deleteById(id);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }

}
