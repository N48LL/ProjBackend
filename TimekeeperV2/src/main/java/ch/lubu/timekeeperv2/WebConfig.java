/* This is an attept to fix CORDS (Uncaught (in promise) TypeError: NetworkError when attempting to fetch resource.) issue on
    Firefox but its probably only NodeJS issue --> NodeJS is mainly for chrome made....apparently we should "Spidermonkey" for Firefox???

package ch.lubu.timekeeperv2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD");
    }
}
*/