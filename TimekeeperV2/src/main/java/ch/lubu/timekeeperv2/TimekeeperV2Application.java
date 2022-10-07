package ch.lubu.timekeeperv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RestController
@CrossOrigin
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
        return "WelcomePage";
    }


    /**
     * Fix for CORS errors -> Access-Control-Allow-Origin
     * second solution: @CrossOrigin
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}
