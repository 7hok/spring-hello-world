package khmerhowto.Repository;

import khmerhowto.Repository.Model.ContentRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 
import java.awt.print.Pageable;
import java.time.LocalDateTime;
 

@RepositoryRestResource(collectionResourceRel = "requests", path = "requests")
public interface ContentRequestRepository extends JpaRepository<ContentRequest,Integer> {

    Page<ContentRequest> findByStatus(int i, org.springframework.data.domain.Pageable pageable);

    @Query("SELECT f FROM ContentRequest f WHERE f.timestamp BETWEEN :start_date AND :end_date")
	Page<ContentRequest> findByDate(LocalDateTime start_date, LocalDateTime end_date,
			org.springframework.data.domain.Pageable pageable);

}
