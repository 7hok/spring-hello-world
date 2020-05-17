package khmerhowto.Repository;

import khmerhowto.Repository.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    List<UserRole> findByUserId(Integer userId);

    UserRole findByUserIdAndRoleIdIsNot(Integer userId,Integer roleId);

}
