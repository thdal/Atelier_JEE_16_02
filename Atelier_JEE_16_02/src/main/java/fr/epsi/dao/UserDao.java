package fr.epsi.dao;

import java.util.List;

import fr.epsi.entite.Roles;
import fr.epsi.entite.User;

public interface UserDao {
    void create(User u); 
    
   List<User> getUsersList(); 
   
   User getUser(String mail, String password);
   
   User getUserOnId(Long id);
   
   void delete(Long id);
   
   void setStatus(Long id, Boolean status);
   
   void setValidated(Long id);

}
