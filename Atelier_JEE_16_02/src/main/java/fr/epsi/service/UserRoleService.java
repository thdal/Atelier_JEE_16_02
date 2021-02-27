package fr.epsi.service;

import java.util.List;

import fr.epsi.entite.UserRole;

public interface UserRoleService {
	
	void createUserRole(UserRole ur);
	
    Long getRoleId(Long userId);


}
