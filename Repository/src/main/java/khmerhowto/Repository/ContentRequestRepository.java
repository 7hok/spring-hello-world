package khmerhowto.Repository;

import khmerhowto.Repository.Model.ContentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.awt.print.Pageable;

@RepositoryRestResource(collectionResourceRel = "requests")
public interface ContentRequestRepository extends JpaRepository<ContentRequest,Integer> {

}
