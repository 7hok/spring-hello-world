package khmerhowto.Service;

import khmerhowto.Repository.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

   Page<User> findAll(Pageable pageable);
    Page<User>findAllByName(String name,Pageable pageable);

}
