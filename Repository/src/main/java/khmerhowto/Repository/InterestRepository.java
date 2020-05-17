package khmerhowto.Repository;

import khmerhowto.Repository.Model.Interested;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource
@Transactional
public interface InterestRepository extends JpaRepository<Interested,Integer> {
    @Query(value = "SELECT Count(i) FROM Interested i where i.content.id=:cId")
    public Integer getTotalLike(@Param("cId") Integer id);

    @Override
    List<Interested> findAll();

    List<Interested> findByuserIdAndContentId(int u_id,int c_id);

    Long deleteByuserIdAndContentId(int u_id,int c_id);
}
