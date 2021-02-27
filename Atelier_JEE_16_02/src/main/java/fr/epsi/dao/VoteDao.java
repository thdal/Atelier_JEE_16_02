package fr.epsi.dao;

import java.util.List;

import fr.epsi.entite.Vote;
	
public interface VoteDao {
    void create(Vote n); 
    
    List<Object[]> getVotesListSortByTopBuzzIdees();    
    
}


