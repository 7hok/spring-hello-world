package khmerhowto.Service.ServiceImplement;

import khmerhowto.Repository.Model.User;
import khmerhowto.Repository.UserRepository;
import khmerhowto.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
private UserRepository userRepository;

    @Override
    public Page<User> findByName(String name,Pageable pageable) {
        return userRepository.findByNameContainingIgnoreCaseAndStatus(name,1,pageable);
    }

    @Override
    public void updateUser(Integer id,User user) {
      User user1 = userRepository.getOne(id);
      user.setEmail(user1.getEmail());
      user.setProfilePicture(user1.getProfilePicture());
        userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findByStatus(1,pageable);
    }

    @Override
    public Page<User> findAllByName(String name,Pageable pageable) {
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

}

