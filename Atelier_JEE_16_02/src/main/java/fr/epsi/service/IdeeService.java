package fr.epsi.service;

import java.util.List;

import fr.epsi.entite.Idee;
import fr.epsi.entite.Vote;

public interface IdeeService {
	
	void createIdee(Idee i);
	
	List<Idee> getIdeesList();
	
	Idee getIdee(Long id);

	
	void setVote(Vote vote, Long id);
	
	void setPercent(int percent, Long id);

}
