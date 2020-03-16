package pl.coderslab.charityproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

@Component
public class EncodingFilter {

    @Autowired
    public EncodingFilter(ServletContext container,
                          AnnotationConfigWebApplicationContext ctx) {

        FilterRegistration.Dynamic fr = container.addFilter("encodingFilter",
                new CharacterEncodingFilter());
        fr.setInitParameter("encoding","UTF-8");
        fr.setInitParameter("forceEncoding","true");
        fr.addMappingForUrlPatterns(null,true,"/*");
    }

}
