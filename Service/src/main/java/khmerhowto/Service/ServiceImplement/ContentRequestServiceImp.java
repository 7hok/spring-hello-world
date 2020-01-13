package khmerhowto.Service.ServiceImplement;

import khmerhowto.Repository.ContentRequestRepository;
import khmerhowto.Repository.Model.ContentRequest;
import khmerhowto.Service.ContentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContentRequestServiceImp implements ContentRequestService {
    @Autowired
    private ContentRequestRepository contentRequestRepository;
    @Override
    public Page<ContentRequest> findAll(Pageable pageable) {
        return contentRequestRepository.findAll(pageable);
    }
}
