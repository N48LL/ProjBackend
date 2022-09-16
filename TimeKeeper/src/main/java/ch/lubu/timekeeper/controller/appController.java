package ch.lubu.timekeeper.controller;

import ch.lubu.timekeeper.model.TimeRepository;
import ch.lubu.timekeeper.model.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class appController {

    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private YearRepository yearRepository;

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
