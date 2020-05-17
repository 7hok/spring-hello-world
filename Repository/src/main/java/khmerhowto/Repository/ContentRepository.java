package khmerhowto.Repository;

import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Content;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;


@RepositoryRestResource
public interface ContentRepository extends JpaRepository<Content, Integer> {

	@Query(nativeQuery = true,value = "SELECT * FROM top_click_category_article_suggestion(:user_id) ORDER BY click DESC NULLS LAST")
	Page<Content> findContentBasedOnUserHistoryClick( @Param("user_id") Integer user_id, @PageableDefault(size = 3) Pageable pageable);

	@Query(nativeQuery = true,value = "SELECT * FROM favorite_category_article_suggestion(:user_id) WHERE status = 1" )
	Page<Content> findContentBasedOnFavoriteCategory( @Param("user_id") Integer user_id,Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT count(*) FROM content WHERE timestamp > :date")
	Integer findUnreadContentByDate(@Param("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime convert);

	Page<Content> findByStatusAndTitleContainingIgnoreCase(Integer st,@Param("title") String title, Pageable pageable);

	List<Content> findAllBycategoryIdAndStatus(Integer id ,Integer st, Pageable pageable);

	Page<Content> findByTitleContainingIgnoreCaseAndCategoryIdAndStatus(@Param("title") String title, Integer c_id, int i, Pageable pageable);
	public Page<Content> findAllByOrderByIdDesc(Pageable pageable);

	Page<Content> findByBodyContainingIgnoreCaseAndStatus(@Param("body") String body,Integer st, Pageable pageable);
	@Query(value = "SELECT click,c.* FROM history_click as h RIGHT JOIN content as c on h.content_id = c.id WHERE status = 1 ORDER BY click desc NULLS LAST " ,nativeQuery = true)
	Page<Content> findPopularContent(Pageable pageable);

    @Query(value = "select * from history_click_category where cate_id = :category_id" , nativeQuery = true)
    Page<Content> findPopularArticleBaseOnClick(@Param("category_id") Integer category_id,Pageable pageable);
	@Transactional
	@Modifying
	@Query(value ="UPDATE content set status = 0 where id = :article_id" ,nativeQuery = true)
	void deleteById(@Param("article_id") Integer id);

	Page<Content> findByStatus(int i, Pageable pageable);
}
