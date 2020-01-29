package khmerhowto.Service;
import khmerhowto.Repository.Model.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ContentService {
    List <Content> findAll();
    public Page<Content> findAll(Pageable pageable);
    public Page<Content> findByName(String string,Pageable pageable);
    public Page<Content> findAllByOrderByIdDesc(Pageable pageable);

}
