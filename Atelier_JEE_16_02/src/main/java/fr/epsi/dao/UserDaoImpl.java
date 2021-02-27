package fr.epsi.dao;

import fr.epsi.entite.Roles;
import fr.epsi.entite.User;
import fr.epsi.entite.UserRole;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;


/*
 * 
 * La classe d'impl�mentation DAO du client, qui me permettra d'acc�der � la base de donn�es.
 */
public class UserDaoImpl implements UserDao{
	
	EntityManager em;    
	UserTransaction utx;
	public UserDaoImpl(EntityManager em, UserTransaction utx) {
		this.em=em;
		this.utx=utx;
	}

	public void create(User u) {
		
			try {
				utx.begin();
				em.persist(u);
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
	
	public List<User> getUsersList(){	    
	    	List<User> users = em.createQuery("select u from User u order by u.mail asc", User.class).getResultList();	   
		    return users;
	}	
	
	public User getUser(String mail, String password) {		
		User user = null;
		 try {
			 user = (User) em.createQuery(
					   "SELECT u FROM User u WHERE u.mail = :mail")
					   .setParameter("mail", mail)
					   .setFirstResult(0)
					   .setMaxResults(1)
					   .getSingleResult();
			  } catch (NoResultException e) {
				  return user;
			  }

		return user;
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			utx.begin();
			User user = em.find(User.class, id);
            em.remove(user);		
			utx.commit();
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
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Transactional
	public void setStatus(Long id, Boolean status) {
		try {
			utx.begin();
			User user = em.find(User.class, id);
			user.setStatus(status);
			em.persist(user);
			utx.commit();
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
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Transactional
	public void setValidated(Long id) {
		try {
			utx.begin();
			User user = em.find(User.class, id);
			user.setValidated(true);
			em.persist(user);
			utx.commit();
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
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	

	public User getUserOnId(Long id) {
		// TODO Auto-generated method stub
		User user = em.find(User.class, id);
		return user;
	}
	
	
	

}
