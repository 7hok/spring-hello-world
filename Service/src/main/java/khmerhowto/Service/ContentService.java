package khmerhowto.Service;

import khmerhowto.Repository.Model.Content; 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
 

public interface ContentService {
     
    public Page<Content> findAll(Pageable pageable);
    public Page<Content> findByName(String string,Pageable pageable);
    public Page<Content> findAllByOrderByIdDesc(Pageable pageable);
    
}
