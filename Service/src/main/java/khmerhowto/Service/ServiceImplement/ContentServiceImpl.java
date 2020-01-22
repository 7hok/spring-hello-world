package khmerhowto.Service.ServiceImplement;


import khmerhowto.Repository.ContentRepository;
import khmerhowto.Repository.Model.Content;
import khmerhowto.Service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentRepository contentRepository;

    @Override
    public List<Content> findAll() {
        return contentRepository.findAll();
    }
}
