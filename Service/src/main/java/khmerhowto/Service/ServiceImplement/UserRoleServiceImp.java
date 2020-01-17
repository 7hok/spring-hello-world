package khmerhowto.Service.ServiceImplement;

import khmerhowto.Repository.Model.Role;
import khmerhowto.Repository.Model.UserRole;
import khmerhowto.Repository.RoleRepository;
import khmerhowto.Repository.UserRoleRepository;
import khmerhowto.Service.UserRoleSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImp implements UserRoleSrvice {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public void updateRole(Integer id, Integer roleId) {
      UserRole userRole = userRoleRepository.findByUserAndRoleIsNot(id,roleId);
      userRole.setRole(new Role(roleId));
      userRoleRepository.save(userRole);

    }

    @Override
    public List<UserRole> findAllUsersRole() {
        return userRoleRepository.findAll();
    }

    @Override
    public List<UserRole> findRoleByUserId(Integer id) {
        return userRoleRepository.findByUser(id);
    }
}
