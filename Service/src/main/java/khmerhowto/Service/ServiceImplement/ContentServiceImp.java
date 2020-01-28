package khmerhowto.Service.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import khmerhowto.Repository.ContentRepository;
import khmerhowto.Repository.Model.Content;
import khmerhowto.Service.ContentService;

/**
 * ContentServiceImp
 */
@Service
public class ContentServiceImp implements ContentService {
    @Autowired
    private ContentRepository contentRepository;

    public Page<Content> findAll(Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    public Page<Content> findByName(String string, Pageable pageable) {
        return contentRepository.findByTitleContainingIgnoreCase(string, pageable);
    }

    @Override
    public Page<Content> findAllByOrderByIdDesc(Pageable pageable) {
        return contentRepository.findAllByOrderByIdDesc(pageable);
    }
 
}