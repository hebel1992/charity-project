package pl.coderslab.charityproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CharityProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityProjectApplication.class, args);
    }

}
