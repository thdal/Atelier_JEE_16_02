package fr.epsi.service;

import java.util.List;

import fr.epsi.entite.Roles;

public interface RolesService {
	
	void createRole(Roles r);	
	
	int getRole(Long id);

}
