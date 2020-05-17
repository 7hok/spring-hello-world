package khmerhowto.Repository;

import khmerhowto.Repository.Model.FavoriteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FavoriteCategoryRepository extends JpaRepository<FavoriteCategory,Integer> {
     List<FavoriteCategory> findAllByCategoryId(Integer cateId);
}
