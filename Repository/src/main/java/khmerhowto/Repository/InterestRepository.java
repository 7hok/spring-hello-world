package khmerhowto.Repository;

import khmerhowto.Repository.Model.Interested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InterestRepository extends JpaRepository<Interested,Integer> {
    @Query(value = "SELECT Count(i) FROM Interested i where i.content.id=7")
    public Integer getTotalLike();
}
