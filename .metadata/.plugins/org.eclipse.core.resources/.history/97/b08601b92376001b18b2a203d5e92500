package fr.epsi.entite;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;

/*
Well it's almost a year late, but I just came across this problem myself today :-)

You can turn off this error in Eclipse. Go to

Preferences->Java Persistence->JPA->Errors/Warnings

Under the Type section look for the category "ID class must be used when multiple ID mappings defined."
 and change it from Error to Ignore (or whatever severity you want to give it).
*/

// Une association


import javax.persistence.ManyToOne;
@Entity
public class UserRole implements Serializable{
	
	@Id
	private Long userId;
	
	@Id
	private Long rolesId;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}	

	public Long getRolesId() {
		return rolesId;
	}

	public void setRolesId(Long rolesId) {
		this.rolesId = rolesId;
	}

}
