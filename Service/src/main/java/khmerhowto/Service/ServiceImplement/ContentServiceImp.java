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

	public Page<Content> findByNameAndCategory(String search_text, Integer c_id, Pageable pageable) {
        Page<Content> pages;
        if(c_id == 0){
            pages = findByName(search_text, pageable);
        }else{
            search_text = (search_text == null )? "" : search_text;
            System.out.println("T : "+search_text +"\n" +"category :"+ c_id +"\n" +"category :"+ c_id +"\n" );
            pages = contentRepository.findByTitleContainingIgnoreCaseAndCategoryIdAndStatus( search_text,c_id,1,pageable);
          //  pages= contentRepository.findByTitleAndCategoryIdAndStatus(search_text, c_id, 1, pageable);
        }
		return pages;
	}
}