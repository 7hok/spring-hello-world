package khmerhowto.Service.ServiceImplement;


import khmerhowto.Repository.CategoryRepository;
import khmerhowto.Repository.Model.Category;
import khmerhowto.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findByStatusOrderByIdDesc(1);
    }
}
