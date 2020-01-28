package khmerhowto.Repository;

import khmerhowto.Repository.Model.Feedback;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
    // @RestResource(rel = "update_status",path = "/delete")
    @Transactional
    @Modifying
    @Query(value = "Update Feedback f set f.status = 0   Where f.id = :id ")
    void deleteById(@Param("id") Integer id);

    @Query("SELECT f FROM Feedback f WHERE f.timestamp BETWEEN :start_date AND :end_date")
	Page<Feedback> findByDate(LocalDateTime start_date, LocalDateTime end_date, Pageable pageable);
}
