package ch.lubu.timekeeperv2.controller;

import ch.lubu.timekeeperv2.model.EntryDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

   // create new entryDate
    @PostMapping(path = "/add")
    public EntryDate saveDate(@RequestBody EntryDate entryDate) {
        return entryDateRepository.save(entryDate);
        //if date already exists return "date already exists"
    }

    // update entryDate by id
    @PutMapping(path = "/update/{id}")
    public EntryDate updateDate(@PathVariable Integer id, @RequestBody EntryDate entryDate) {
        EntryDate entryDateToUpdate = entryDateRepository.findById(id).get();

        entryDateToUpdate.setDate(entryDate.getDate());
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
