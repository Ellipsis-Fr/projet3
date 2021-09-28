package fr.isika.projet3.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import fr.isika.projet3.enumerations.Statut;

@Entity
@Table(name="Partners")
public class Partner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	private Statut statut;
	private String password;
	
	@OneToMany(mappedBy="partner", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Activity> activities;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UserSociety userSociety;
	
	public Partner() {
		super();
		activities = new ArrayList<>();
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
	
	public List<Activity> getActivities() {
		return activities;
	}
	
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public UserSociety getUserSociety() {
		return userSociety;
	}

	public void setUserSociety(UserSociety userSociety) {
		this.userSociety = userSociety;
	}
	
	
}
