package khmerhowto.Repository;

import khmerhowto.Repository.Model.Comment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
 
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
 

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Integer> {

public Page<Comment> findAllBycontentIdAndStatusOrderById(Integer id, Integer st,Pageable pageable);

@Query(value = "SELECT Count(c) FROM Comment c where c.content.id=:cId")
public Integer getTotalComment(@Param("cId") Integer id);

public Page<Comment> findAllByStatusOrderByIdDesc(Integer st,Pageable pageable);
@Query(nativeQuery = true, value = "SELECT count(*) FROM comment WHERE timestamp > :date")
   Integer findUnreadCommentByDate(@Param("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime convert);
    }
