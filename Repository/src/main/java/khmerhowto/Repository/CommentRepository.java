package khmerhowto.Repository;

import khmerhowto.Repository.Model.Comment;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
   public Page<Comment> findAllBycontentIdOrderByTimestamp( Integer id,Pageable pageable);

   @Query(value = "SELECT Count(c) FROM Comment c")
   public Integer getTotalComment();
}
