package khmerhowto.Service;

import khmerhowto.Repository.Model.UserRole;

import java.util.List;

public interface UserRoleSrvice {
    void updateRole(Integer userId,Integer roleId);
    List<UserRole> findAllUsersRole();
    List<UserRole> findRoleByUserId(Integer id);

}
