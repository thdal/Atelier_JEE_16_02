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
import fr.epsi.entite.Idee;
import fr.epsi.entite.Vote;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class IdeeServiceImpl implements IdeeService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
    private UserTransaction utx;
	
	public void createIdee(Idee i) {		
		IdeeDao dao=new IdeeDaoImpl(em, utx);
		dao.create(i);		
	}
	
	public List<Idee> getIdeesList() {
		IdeeDao dao=new IdeeDaoImpl(em, utx);
		return dao.getIdeesList();		
	}
	
	public Idee getIdee(Long ideeId) {
		IdeeDao dao=new IdeeDaoImpl(em, utx);
		return dao.getIdee(ideeId);		
	}
	
	public void setVote(Vote vote, Long id) {		
		IdeeDao dao=new IdeeDaoImpl(em, utx);
		dao.setVote(vote, id);			
	}
	
	public void setPercent(int percent, Long id) {		
		IdeeDao dao=new IdeeDaoImpl(em, utx);
		dao.setPercent(percent, id);			
	}

}
