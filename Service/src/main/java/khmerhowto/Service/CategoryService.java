package khmerhowto.Service;

import khmerhowto.Repository.Model.Category;
import khmerhowto.Repository.Model.Role;

import java.util.List;

public interface CategoryService {
    List <Category> findAll();
}
