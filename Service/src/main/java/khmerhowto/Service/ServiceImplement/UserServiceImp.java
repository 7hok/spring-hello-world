package khmerhowto.Service.ServiceImplement;

import khmerhowto.Repository.Model.User;
import khmerhowto.Repository.UserRepository;
import khmerhowto.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService {
    @Autowired
private UserRepository userRepository;



    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllByName(String name,Pageable pageable) {
        return null;
    }
}
