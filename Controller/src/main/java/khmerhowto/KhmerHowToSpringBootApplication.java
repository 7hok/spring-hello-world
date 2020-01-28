package khmerhowto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
// import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@SpringBootApplication
@RestController
// @Import(SpringDataRestConfiguration.class)
public class KhmerHowToSpringBootApplication extends SpringBootServletInitializer {
    public static void main(String[] args){

        SpringApplication.run(KhmerHowToSpringBootApplication.class , args);

    }
    @Override 
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(KhmerHowToSpringBootApplication.class);
    }

}
