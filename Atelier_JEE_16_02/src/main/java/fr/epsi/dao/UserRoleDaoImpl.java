package fr.epsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.entite.Roles;
import fr.epsi.entite.User;
import fr.epsi.entite.UserRole;

public class UserRoleDaoImpl implements UserRoleDao{
	
	EntityManager em;
	UserTransaction utx;
	public UserRoleDaoImpl(EntityManager em, UserTransaction utx) {
		this.em=em;
		this.utx=utx;
	}

	public void create(UserRole ur) {
		try {
			utx.begin();
			em.persist(ur);
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
	
	public List<UserRole> getRoleId(Long id) {
		// Pas de PK Pas de find();
		List<UserRole> roles = null;
	    try {
	    	roles = em.createQuery("select ur from UserRole ur WHERE ur.userId = ?1", UserRole.class)
					.setParameter(1,  id)
				    .getResultList();   
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return roles;
	}
	
	

}
