package ch.lubu.timekeeperv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TimekeeperV2Application {

    public static void main(String[] args) {
        SpringApplication.run(TimekeeperV2Application.class, args);
    }
    @GetMapping("/")
    public String index() {
        /**
         * mapping for root
         * @see appController
         * @return ??
         * todo: this should return nothing??? or htmnl page???
         */
        return "WelcomePage";
    }
}
