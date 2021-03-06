package fr.isika.projet3.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import fr.isika.projet3.enumerations.Statut;

@Entity	
@Table(name="Partners")
public class Partner implements IRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 1000)
	private String description;
	private Statut statut;
	private String password;
	
	@OneToMany(mappedBy="partner", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Finance> finances;
	
	@OneToMany(mappedBy="partner", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Activity> activities;

//	@OneToOne(fetch = FetchType.EAGER)
//	private UserSociety userSociety;
	
	@Transient
	private String role = "partner";

	public Partner() {
		super();
		activities = new ArrayList<>();
		finances = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public String getRole() {
		return role;
	}
	
//	public UserSociety getUserSociety() {
//		return userSociety;
//	}
//
//	public void setUserSociety(UserSociety userSociety) {
//		this.userSociety = userSociety;
//	}

	
}
