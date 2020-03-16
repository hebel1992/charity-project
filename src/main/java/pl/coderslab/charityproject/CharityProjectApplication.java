package pl.coderslab.charityproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

@SpringBootApplication
public class CharityProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CharityProjectApplication.class, args);
    }

}
