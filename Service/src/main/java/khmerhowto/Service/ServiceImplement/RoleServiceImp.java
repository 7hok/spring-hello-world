package khmerhowto.Service.ServiceImplement;

import khmerhowto.Repository.Model.Role;
import khmerhowto.Repository.RoleRepository;
import khmerhowto.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
