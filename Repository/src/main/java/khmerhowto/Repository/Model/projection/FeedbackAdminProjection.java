package khmerhowto.Repository.Model.projection;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import khmerhowto.Repository.Model.Content;
import khmerhowto.Repository.Model.Feedback;
import khmerhowto.Repository.Model.User;

/**
 * feedbackAdminProjection
 */
@Projection(name = "feedbackAdminProjection", types = { Feedback.class }) 
 public interface FeedbackAdminProjection {
    @Value("#{target.id}")
    int getId();
    String getText();
    LocalDateTime getTimestamp();
    ContentInfo getContent();
    UserInfo getUser();
    
    /**
     * InnerFeedbackAdminProjection
     */
    interface UserInfo {
    
        String getName();
        String getProfilePicture();
    }

    /**
     * InnerFeedbackAdminProjection
     */
    interface ContentInfo {
    
        String getTitle();
    }
}