package fr.epsi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.dao.IdeeDao;
import fr.epsi.dao.IdeeDaoImpl;
import fr.epsi.dao.UserDao;
import fr.epsi.dao.UserDaoImpl;
import fr.epsi.entite.User;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceImpl implements UserService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
    private UserTransaction utx;
	
	public void createUser(User c) {		
		UserDao dao=new UserDaoImpl(em, utx);
		dao.create(c);		
	}
	
	public List<User> getUsersList() {
		UserDao dao=new UserDaoImpl(em, utx);		
		return dao.getUsersList();		
	}

	public Boolean getUserExist(String mail, String password) {
		
		UserDao dao=new UserDaoImpl(em, utx);		
		User user = dao.getUser(mail, password);		
		if(user == null) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public Long getUserId(String mail, String password) {

		UserDao dao=new UserDaoImpl(em, utx);	
		User user = dao.getUser(mail, password);
		Long user_id = null;
		
		if(user == null) {
			return null;
		}else {
			user_id = user.getId();
		}
		System.out.println("userId");
		System.out.println(user_id);		
		
		return user_id;
	}
	
	public void delete(Long id) {
		
		UserDao dao=new UserDaoImpl(em, utx);	
		dao.delete(id);
		
	}
	
	public void setStatus(Long id, Boolean status) {
			
			UserDao dao=new UserDaoImpl(em, utx);	
			dao.setStatus(id, status);			
		}
	
	public void setValidated(Long id) {
		
		UserDao dao=new UserDaoImpl(em, utx);	
		dao.setValidated(id);
		
	}

	public User getUserOnId(Long id) {
		UserDao dao=new UserDaoImpl(em, utx);	
		return dao.getUserOnId(id);		
	}
	
	public Boolean getUserStatus(Long id) {
		UserDao dao=new UserDaoImpl(em, utx);	
		User u = dao.getUserOnId(id);	
		return u.getStatus();
	}
	
	

}
