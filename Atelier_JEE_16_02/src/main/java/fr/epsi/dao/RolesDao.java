package fr.epsi.dao;

import java.util.List;

import fr.epsi.entite.Roles;

public interface RolesDao {
    void createRole(Roles r); 
        
   Roles getRole(Long id);

}
