package ch.lubu.timekeeperv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@SpringBootApplication
public class TimekeeperV2Application {

    public static void main(String[] args) {
        SpringApplication.run(TimekeeperV2Application.class, args);
    }
    @GetMapping("/")
    public String index() {
        /**
         * This method returns the index page.
         * mapping for root
         * @see appController
         * @author Lukas Bühler
         * @version 2.0
         */
        return "TimeKeeper REST API!";
    }


}
