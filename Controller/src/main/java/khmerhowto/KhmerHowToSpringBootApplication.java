package khmerhowto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableOAuth2Sso

public class KhmerHowToSpringBootApplication extends SpringBootServletInitializer {
    public static void main(String[] args){

        SpringApplication.run(KhmerHowToSpringBootApplication.class , args);

    }
    @Override 
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(KhmerHowToSpringBootApplication.class);
    }

}
