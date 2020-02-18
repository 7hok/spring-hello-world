package khmerhowto.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileUploadConfigure implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         registry.addResourceHandler("/img/**").addResourceLocations("file:Controller/src/main/resources/static/Images/");
       registry.addResourceHandler("/img/**").addResourceLocations("file:/home/howto/images/");
    }
}

