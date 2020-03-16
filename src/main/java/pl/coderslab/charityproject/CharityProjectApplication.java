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
        System.setProperty("file.encoding","UTF-8");
        Field charset = null;
        try {
            charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null,null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        SpringApplication.run(CharityProjectApplication.class, args);
    }

}
