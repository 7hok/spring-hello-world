package khmerhowto.Repository.Model.projection;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import khmerhowto.Repository.Model.Content;

/**
 * ContentProject
 */
@Projection(name = "ContentNotificationProjection", types = { Content.class }) 
public interface ContentProject {
    @Value("#{target.id}")
    Integer getId();
    String getBody();
    CategoryInfo getCategory();
    String getThumbnail();
    String getTitle();
    interface CategoryInfo {
        String getName();
    }
    LocalDateTime getTimestamp();
}