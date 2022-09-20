package ch.lubu.timekeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.lubu.timekeeper.model.Time;
import ch.lubu.timekeeper.model.TimeRepository;

/**
 * @author Lukas Bühler
 * @version 1.0
 *
*/

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;



}