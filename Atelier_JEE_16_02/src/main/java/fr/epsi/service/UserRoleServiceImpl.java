package fr.epsi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.dao.UserDao;
import fr.epsi.dao.UserDaoImpl;
import fr.epsi.dao.UserRoleDao;
import fr.epsi.dao.UserRoleDaoImpl;
import fr.epsi.entite.User;
import fr.epsi.entite.UserRole;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserRoleServiceImpl implements UserRoleService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
    private UserTransaction utx;
	
	public void createUserRole(UserRole ur) {		
		UserRoleDao dao = new UserRoleDaoImpl(em, utx);
		dao.create(ur);		
	}

	public Long getRoleId(Long userId) {
		UserRoleDao dao=new UserRoleDaoImpl(em, utx);	
		List<UserRole> roles = dao.getRoleId(userId);
		Long role_id = null;
		if(roles == null) {
			return null;
		}
		if(roles.size() == 0) {
			return null;
		}
		for(UserRole ur : roles) {
			System.out.println(ur.getRolesId());
			System.out.println(ur.getRolesId());
			role_id = ur.getRolesId();

		}
		
		return role_id;
	}


}
