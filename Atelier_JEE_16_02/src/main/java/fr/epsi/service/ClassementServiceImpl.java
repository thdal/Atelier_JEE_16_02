package fr.epsi.service;

import java.util.ArrayList;
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
import fr.epsi.dao.VoteDao;
import fr.epsi.dao.VoteDaoImpl;
import fr.epsi.entite.Idee;
import fr.epsi.entite.User;
import fr.epsi.entite.Vote;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ClassementServiceImpl implements ClassementService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
    private UserTransaction utx;
	
	public List<Idee> getClassementTops() {
		IdeeDao daoIdee=new IdeeDaoImpl(em, utx);	
		List<Idee> idees = daoIdee.getIdeesList();			
		for(Idee i : idees) {

			int nbdetop=0;
			if(i.getVote().size() != 0) {
				for(Vote v : i.getVote()) {
					if(v.getLabelVote() == 1) {
						nbdetop++;
					}				
				}
				int t = 100;
				int x = nbdetop;
				int y = i.getVote().size();
		        double value = ((double) x) / y;
		        double pourcentage = ((double) t) * value;
				daoIdee.setPercent((int)pourcentage, i.getId());

			}else {
				daoIdee.setPercent(nbdetop, i.getId());
			}

		}
		List<Idee> ideesUpdated = daoIdee.getIdeesListSortByPercent();
		List<Idee> listTops = daoIdee.getIdeesListSortByPercent();
		int j = 0;
		int k = 0;			

		for(Idee ibis : ideesUpdated) {
			for(Idee lt : listTops) {
				if(k != 0) {
					// 1er crit�re
					if(lt.getTopPercentage() == ibis.getTopPercentage()) {
						// 2eme crit�re
						if(lt.getVote().size() > ibis.getVote().size()) {
							listTops.set(j, lt);
							listTops.set(k, ibis);
						}
						// 3eme crit�re
						else if (lt.getVote().size() == ibis.getVote().size()) {
							// > 0 = la date plus ancienne � celle pass�e en argument
							if(lt.getDateEmission().compareTo(ibis.getDateEmission()) > 0) {
								listTops.set(j, lt);
								listTops.set(k, ibis);
							}
						}
					}
				}				
				k++;//for 2
				if(k == 2 || j ==2)
					break;
			}			
			j++;//for 1
				if(k == 2 || j == 2)
					break;
			
		}
		
		return listTops;
	}
		
	public List<User> getClassementBrains() {
		IdeeDao daoIdee=new IdeeDaoImpl(em, utx);
		UserDao daoUser=new UserDaoImpl(em, utx);	

		List<Object[]> listObj = daoIdee.getIdeesListSortByTopUserWriters();
		List<User> users = new ArrayList<User>();
		
		for (Object[] result : listObj) {
		      users.add(daoUser.getUserOnId((Long)result[0]));		      
		  }
		return users;
	}
	
	public List<Idee> getClassementBuzz() {
		VoteDao daoVote =new VoteDaoImpl(em, utx);
		IdeeDao daoIdee=new IdeeDaoImpl(em, utx);	

		List<Object[]> listObj = daoVote.getVotesListSortByTopBuzzIdees();
		List<Idee> idees = new ArrayList<Idee>();
		
		for (Object[] result : listObj) {
			idees.add(daoIdee.getIdee((Long)result[0]));		      
		  }
		return idees;
	}
}