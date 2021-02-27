package fr.epsi.service;

import java.util.List;

import fr.epsi.entite.Idee;
import fr.epsi.entite.User;

public interface ClassementService {
	
	List<Idee> getClassementTops();
	
	List<User> getClassementBrains();
	
	List<Idee> getClassementBuzz();
	
}
