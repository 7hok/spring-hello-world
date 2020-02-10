package khmerhowto.Repository.Configuration;

import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Content;
import khmerhowto.Repository.Model.ContentRequest;
import khmerhowto.Repository.Model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(ContentRequest.class,Category.class,User.class, Content.class);

    }
}
