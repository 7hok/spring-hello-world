package khmerhowto.Service.ServiceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khmerhowto.Repository.CategoryRepository;
import khmerhowto.Repository.Model.Category;

/**
 * CategoryServiceImp
 */

@Service
public class CategoryServiceImp {

   @Autowired
   private CategoryRepository categoryRepository;
    /**
     * To find category base on status deleted = 0 active =1 
     * NOTE : current on pre mode every data is import whether checking status
     * 
     * 
     * @param status
     * @return
     */
   public List<Category> findCategoryByStatus(Integer status){

    return categoryRepository.findAll();
   }
	public Category findCategoryById(Integer id) {
		return categoryRepository.getOne(id);
	}
}