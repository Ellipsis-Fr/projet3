package fr.isika.projet3.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.isika.projet3.enumerations.Statut;

@Entity
@Table(name="Partner")
public class Partner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	private Statut statut;
	private String email;
	private String password;
	@OneToMany(mappedBy="partner", fetch = FetchType.EAGER)
	private List<Finance> finances;
	
	public Partner() {
		super();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) { 
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Finance> getFinances() {
		return finances;
	}
	public void setFinances(List<Finance> finances) {
		this.finances = finances;
	}
	

}
