package khmerhowto.Repository;

import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Content;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;


@RepositoryRestResource
public interface ContentRepository extends JpaRepository<Content, Integer> {
	@Query(nativeQuery = true,value = "select * from favorite_category_article_suggestion(:user_id)")
	Page<Content> findContentBasedOnFavoriteCategory( @Param("user_id") Integer user_id,Pageable pageable);
	@Query(nativeQuery = true, value = "SELECT count(*) FROM content WHERE timestamp > :date")
	Integer findUnreadContentByDate(@Param("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime convert);

	Page<Content> findByTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);

	List<Content> findAllBycategoryIdAndStatus(Integer id ,Integer st, Pageable pageable);

	Page<Content> findByTitleContainingIgnoreCaseAndCategoryIdAndStatus(@Param("title") String title, Integer c_id, int i, Pageable pageable);
	public Page<Content> findAllByOrderByIdDesc(Pageable pageable);

	Page<Content> findByBodyContainingIgnoreCase(@Param("body") String body, Pageable pageable);
	@Query(value = "select click,c.* from history_click as h right join content as c on h.content_id = c.id where status = 1" ,nativeQuery = true)
	Page<Content> findPopularContent(Pageable pageable);
}
