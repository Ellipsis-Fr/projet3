package fr.isika.projet3.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="UsersSociety")
public class UserSociety extends User {
	
	private String companyName;
	private String siret;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Partner partner;
	
	public UserSociety() {
		super();
	}

	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getSiret() {
		return siret;
	}
	
	public void setSiret(String siret) {
		this.siret = siret;
	}
}
