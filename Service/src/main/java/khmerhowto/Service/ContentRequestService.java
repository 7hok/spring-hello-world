package khmerhowto.Service;

import khmerhowto.Repository.Model.ContentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentRequestService {
    Page<ContentRequest> findAll(Pageable pageable);
}
