package fr.epsi.entite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    private User user;	

	@ManyToOne
    private Idee idee;
	
	//Pas d'enum, un entier 
	//1 = top 
	//0 = flop
	private int labelVote;

		
	public int getLabelVote() {
		return labelVote;
	}

	public void setLabelVote(int labelVote) {
		this.labelVote = labelVote;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Idee getIdee() {
		return idee;
	}

	public void setIdee(Idee idee) {
		this.idee = idee;
	}

}