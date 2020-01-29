package khmerhowto.Repository;

import khmerhowto.Repository.Model.Category;

import java.util.List;

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

    @Query("SELECT  c FROM Category c WHERE c.id = 1")
    List<Category> findByCategoryIdAndStatus();

	List<Category> findByStatus(Integer status);

}
