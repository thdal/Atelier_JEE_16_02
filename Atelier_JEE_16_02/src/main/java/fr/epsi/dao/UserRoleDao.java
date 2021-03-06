package fr.epsi.dao;

import java.util.List;

import fr.epsi.entite.User;
import fr.epsi.entite.UserRole;

public interface UserRoleDao {
    void create(UserRole ur); 
    
    List<UserRole> getRoleId(Long userId);
    
}
