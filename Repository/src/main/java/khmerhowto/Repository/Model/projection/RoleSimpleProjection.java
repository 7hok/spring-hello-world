package khmerhowto.Repository.Model.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import khmerhowto.Repository.Model.UserRole;

/**
 * RoleSimpleProjection
 */
@Projection(name = "RoleSimpleProjection", types = { UserRole.class }) 
public interface RoleSimpleProjection {
    @Value("#{target.id}")
    Integer getId();

    UserInfo getUser();

    interface UserInfo {
    
        Integer getId();
        String getEmail();
    }

    RoleInfo getRole();

    interface RoleInfo {
        Integer getId();
        String getName();
    }
}