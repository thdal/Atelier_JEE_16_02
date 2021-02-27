package fr.epsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.entite.Vote;

public class VoteDaoImpl implements VoteDao{
	
	EntityManager em;
	UserTransaction utx;
	public VoteDaoImpl(EntityManager em, UserTransaction utx) {
		this.em=em;
		this.utx=utx;
	}

	public void create(Vote n) {
		
			try {
				utx.begin();
				em.persist(n);
				utx.commit();
			} catch (NotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicMixedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	public List<Object[]> getVotesListSortByTopBuzzIdees(){	    
    	List<Object[]> idees = em.createQuery("select v.idee.id , count(v.idee.id) AS cnt from Vote v GROUP by v.idee.id order by cnt desc", Object[].class)
    			.setMaxResults(3)
    			.getResultList();	   
	    return idees;
	}

}
