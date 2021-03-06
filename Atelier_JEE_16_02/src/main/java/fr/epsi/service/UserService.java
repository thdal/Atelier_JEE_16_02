package fr.epsi.service;

import java.util.List;

import fr.epsi.entite.User;

public interface UserService {
	
	void createUser(User u);
	
	List<User> getUsersList();
	
	Long getUserId(String mail, String password);
	
	User getUserOnId(Long id);
	
	Boolean getUserExist(String mail, String password);
	
	void delete(Long id);
	
	void setStatus(Long id, Boolean status);
	   
	void setValidated(Long id);
	
	Boolean getUserStatus(Long id);

}
