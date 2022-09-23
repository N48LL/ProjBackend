package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.Dto.EntryDateDto;
import ch.lubu.timekeeperv2.exception.DateCouldNotBeSavedException;
import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * This class is the controller for the date entity.
 * @author Lukas Bühler
 * @version 2.0
 */

@RestController
@CrossOrigin
@RequestMapping("/date")
public class EntryDateController {

    @Autowired
    private EntryDateRepository entryDateRepository;

    // Show ALL dates
    @GetMapping(path = "/all")
    public Iterable<EntryDate> getAllDates() {
        return entryDateRepository.findAll();
    }

    // show all by year
    @GetMapping(path = "/{year}")
    public Iterable<EntryDate> getDatesByYear(@PathVariable Integer year) {
        return entryDateRepository.findByYear(year);
    }
    // show all by year + month
    @GetMapping(path = "/{year}/{month}")
    public Iterable<EntryDate> getDatesByMonth(@PathVariable Integer year, @PathVariable Integer month) {
        return entryDateRepository.findByMonth(year, month);
    }
    // show single day by year + month + day
    @GetMapping(path = "/{year}/{month}/{day}")
    public EntryDate getDatesByDay(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return entryDateRepository.findByDay(year, month, day);
    }

   // create new entryDate
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

    // delete entryDate
    @DeleteMapping(path = "/delete/{id}")
    public HttpStatus deleteDate(@PathVariable int id) {
        entryDateRepository.deleteById(id);
        return HttpStatus.OK;
    }

}
