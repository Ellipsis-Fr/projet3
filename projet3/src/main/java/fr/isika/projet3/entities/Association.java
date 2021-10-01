package fr.isika.projet3.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Associations")
public class Association implements IRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String address;
	private String email;
	private String password;
	private String pathFolder;
	private String pathLogo;
	private String url;
	private String rna;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Document document;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Event event;
	
	private boolean eventInProgress;
	
	@OneToMany(mappedBy="association", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<User> users;
	
	public Association() {
		super();
		users = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
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
	
	public String getPathFolder() {
		return pathFolder;
	}

	public void setPathFolder(String pathFolder) {
		this.pathFolder = pathFolder;
	}

	public String getPathLogo() {
		return pathLogo;
	}
	
	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getRna() {
		return rna;
	}
	
	public void setRna(String rna) {
		this.rna = rna;
	}
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public boolean isEventInProgress() {
		return eventInProgress;
	}

	public void setEventInProgress(boolean eventInProgress) {
		this.eventInProgress = eventInProgress;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Association [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", password="
				+ password + ", rna=" + rna + ", event=" + event + ", eventInProgress=" + eventInProgress + "]";
	}
}
