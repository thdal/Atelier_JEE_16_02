package fr.epsi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.dao.CommentaireDao;
import fr.epsi.dao.CommentaireDaoImpl;
import fr.epsi.entite.Commentaire;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CommentaireServiceImpl implements CommentaireService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
    private UserTransaction utx;
	
	public void createCommentaire(Commentaire c) {		
		CommentaireDao dao=new CommentaireDaoImpl(em, utx);
		dao.create(c);		
	}

}
