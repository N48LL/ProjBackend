package ch.lubu.timekeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TimeKeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeKeeperApplication.class, args);
    }
    @GetMapping("/")
    public String index() {
        return "Spring Boot Wizz-Quiz REST API!";
    }
}
