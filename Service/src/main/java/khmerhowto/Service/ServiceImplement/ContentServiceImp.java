package khmerhowto.Service.ServiceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import khmerhowto.Repository.ContentRepository;
import khmerhowto.Repository.Model.Content;

/**
 * ContentServiceImp
 */
@Service
public class ContentServiceImp {
    @Autowired
    private ContentRepository contentRepository;

	public Page<Content> findAll(Pageable pageable) {
		return contentRepository.findAll(pageable);
	}

	public Page<Content> findByName(String string,Pageable pageable) {
        return contentRepository.findByTitleContainingIgnoreCase(string,pageable);
    }
}