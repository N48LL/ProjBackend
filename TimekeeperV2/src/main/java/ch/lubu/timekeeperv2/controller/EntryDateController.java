package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.Dto.EntryDateDto;
import ch.lubu.timekeeperv2.exception.DateCouldNotBeSavedException;
import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This class is the controller for the {@link EntryDate} entity.
 * @author Lukas Bühler
 * @see EntryDateRepository
 */

@RestController
@CrossOrigin
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
    public Iterable<EntryDate> getAllDates() {
        return entryDateRepository.findAll();
    }

    // show all by year
    @GetMapping(path = "/{year}")
    public Iterable<EntryDate> getDatesByYear(@PathVariable Integer year) {
        return entryDateRepository.findByYear(year);
    }
    // show single day by year + month + day
    @GetMapping(path = "/{year}/{month}/{day}")
    public EntryDate getDatesByDay(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return entryDateRepository.findByDay(year, month, day);
    }

    /**
     * This method fetches all days from a given month and year.
     * @link EntryDateRepository.java -> findByMonth
     * @param year
     * @param month
     * @return List of EntryDate
     */
    @GetMapping(path = "/{year}/{month}")
    public Iterable<EntryDate> getDatesByMonth(@PathVariable Integer year, @PathVariable Integer month) {
        return entryDateRepository.findByMonth(year, month);
    }

    /**
     * This method fetch all Data by year and returns a list of all avalable years.
     * @link EntryDateRepository.java -> findDistriktYears()
     * @return List of all available years
     */
    @GetMapping(path = "/years")
    public Iterable<Integer> getYears() {
        return entryDateRepository.findDistrictYears();
    }

    /**
     * This method is used to fetch a list of months by year.
     * @link EntryDateRepository.java -> findByMonth
     * @return List of months
     * @param year, month
     */
    @GetMapping(path = "/{year}/months")
    public Iterable<Integer> getMonthsByYear(@PathVariable Integer year) {
        return entryDateRepository.findDistrictMonths(year);
    }

    /**
     * This method is used to add a new date with comment.
     * @param Year, Month, Day, Comment
     * @link EntryDateRepository.java -> save
     * @throws ParseException
     */
    @PostMapping(path = "/add")
    public EntryDate saveDate(@RequestBody EntryDateDto Dto) throws ParseException {
        EntryDate t = new EntryDate();

        t.setDate(Dto.getYear(), Dto.getMonth(), Dto.getDay());
        t.setComment(Dto.getComment());
        try {
            return entryDateRepository.save(t);
        } catch (Exception ex) {
            throw new DateCouldNotBeSavedException(t);
        }
        //if date already exists return "date already exists"
    }
    // update entryDate by id
    @PutMapping(path = "/update/{id}")
    public EntryDate updateDate(@PathVariable Integer id, @RequestBody EntryDateDto entryDate) throws ParseException {
        EntryDate entryDateToUpdate = entryDateRepository.findById(id).get();

        entryDateToUpdate.setDate(entryDate.getYear(), entryDate.getMonth(), entryDate.getDay());
        entryDateToUpdate.setComment(entryDate.getComment());
        return entryDateRepository.save(entryDateToUpdate);
    }

    /**
     * This method deletes a date by id.
     * @param id
     * @return HttpStatus
     * @link EntryDateRepository.java
     */
    @DeleteMapping(path = "/delete/{id}")
    public HttpStatus deleteDate(@PathVariable int id) {
        entryDateRepository.deleteById(id);
        return HttpStatus.OK;
    }

    /**
     * This method is used to fetch the sum of all times by day.
     * @link EntryDateRepository.java -> findSumByMonth
     * @param year, month, day
     * @return sum of all times by day in form of hh:mm:ss
     */
    @GetMapping(path = "/{year}/{month}/sum")
    public Iterable<Time> getSumByMonth(@PathVariable Integer year, @PathVariable Integer month) {
        return entryDateRepository.findSumByMonth(year, month);
    }

    // create new date by year + month + day + comment. If date already exists return id of existing date
    @PostMapping(path = "/addnew")
    public Integer saveNewDate(@RequestBody EntryDateDto Dto) throws ParseException {
        EntryDate t = new EntryDate();

        t.setDate(Dto.getYear(), Dto.getMonth(), Dto.getDay());
        t.setComment(Dto.getComment());
        String dateString = Dto.getYear() + "." + Dto.getMonth() + "." + Dto.getDay();
        Date tempDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        Optional<Integer> existingDate = entryDateRepository.findIdByDate(tempDate);
        return existingDate.orElseGet(() -> entryDateRepository.save(t).getId());
    }

}
