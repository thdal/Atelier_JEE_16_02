package fr.epsi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.dao.RolesDao;
import fr.epsi.dao.RolesDaoImpl;
import fr.epsi.dao.UserDao;
import fr.epsi.dao.UserDaoImpl;
import fr.epsi.entite.Roles;
import fr.epsi.entite.User;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class RolesServiceImpl implements RolesService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
    private UserTransaction utx;
	
	public void createRole(Roles r) {		
		RolesDao dao = new RolesDaoImpl(em, utx);
		dao.createRole(r);		
	}

	public int getRole(Long id) {
		RolesDao dao=new RolesDaoImpl(em, utx);	
		Roles role = dao.getRole(id);
		
		if(role == null) {
			return 0;
		}
		int role_num = role.getRoleNumeric();		
		
		return role_num;
	}

}
