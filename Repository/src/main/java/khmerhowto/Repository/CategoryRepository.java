package khmerhowto.Repository;

import khmerhowto.Repository.Model.Category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @RestResource(rel = "update_status",path = "/delete")
    @Transactional
    @Modifying
    @Query(value = "Update Category c set c.status = 0 Where c.id = :id ")
    void deleteById(@Param("id") Integer id);

    List<Category> findByIdAndStatus( Integer id, Integer st);

    List<Category> findByStatus(Integer status);
    /**
     * TODO  : FIND POPULAR ARTICLE BASED ON ARTICLE;
     *
     * history_click_category = view
     *  combine history_click(view) + inner join category
     */

//    Category findByIdAndStatus(Integer id, int i);
}
