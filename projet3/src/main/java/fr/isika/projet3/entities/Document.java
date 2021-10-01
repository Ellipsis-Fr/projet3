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
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import fr.isika.projet3.enumerations.Statut;

@Entity
@Table(name="Documents")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Statut statut;
	private String pathHeader;
	
	@Column(length = 4000)
	private String paragraph_1;
	
	@Column(length = 4000)
	private String paragraph_2;
	
	@OneToMany(mappedBy="document", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Photo> photos;

	public Document() {
		super();
		photos = new ArrayList<>();
		statut = Statut.PENDING;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getPathHeader() {
		return pathHeader;
	}

	public void setPathHeader(String pathHeader) {
		this.pathHeader = pathHeader;
	}

	public String getParagraph_1() {
		return paragraph_1;
	}

	public void setParagraph_1(String paragraph_1) {
		this.paragraph_1 = paragraph_1;
	}

	public String getParagraph_2() {
		return paragraph_2;
	}

	public void setParagraph_2(String paragraph_2) {
		this.paragraph_2 = paragraph_2;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
}
