package khmerhowto.Repository;

import khmerhowto.Repository.Model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HistoryRepository extends JpaRepository<History,Integer> {
}
