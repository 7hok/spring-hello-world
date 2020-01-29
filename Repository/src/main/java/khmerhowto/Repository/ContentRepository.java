package khmerhowto.Repository;

import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Content;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ContentRepository extends JpaRepository<Content, Integer> {

	Page<Content> findByTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);

	Category findContentsByCategoryId(Integer id){
		return findFirst2ByCategoryId(id);
	};
	List<Content> findFirst2ByCategoryId(Integer id);

}
