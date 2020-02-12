package khmerhowto.Repository;

import khmerhowto.Repository.Model.Content;
import khmerhowto.Repository.Model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAllByName(@Param("name") String name);
    List<User>findByNameContainingIgnoreCase(String name);
	Page<User> findByNameContainingIgnoreCaseAndStatus(String name, int i, Pageable pageable);
	Page<User> findByStatus(int i, Pageable pageable);
	User findByEmail(String username);

}
