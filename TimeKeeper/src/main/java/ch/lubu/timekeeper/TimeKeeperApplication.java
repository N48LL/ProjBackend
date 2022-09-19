package ch.lubu.timekeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring boot application - Backend for timekeeper
 * @author Lukas Buehler
 * @version 1.0
 * @uses: maven, spring boot, mysql connector, spring data jpa, spring web
 * @see resources/application.properties
 */

@RestController
@SpringBootApplication
public class TimeKeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeKeeperApplication.class, args);
    }
    @GetMapping("/")
    public String index() {
        /**
         * mapping for root
         * @see appController
         * @return ??
         * todo: this should return nothing??? or htmnl page???
         */
        return "Spring Boot Wizz-Quiz REST API!";
    }
}
