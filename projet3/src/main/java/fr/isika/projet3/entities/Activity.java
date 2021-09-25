package fr.isika.projet3.entities;

import java.time.LocalDate;

import javax.persistence.*;

import fr.isika.projet3.enumerations.Category;
import fr.isika.projet3.enumerations.Statut;

@Entity
@Table(name="Activities")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String address;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
	private String pathPhoto;
	private int necessaryFunding;
	private int allocatedFunding;
	private int volunteerNeeded;
	private int volunteerAllocated;
	private Statut statut;
	private Category category;
	
	@ManyToOne
	private Partner partner;
	
	@ManyToOne
	private Event event;
	
	public Activity() {
		super();
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPathPhoto() {
		return pathPhoto;
	}

	public void setPathPhoto(String pathPhoto) {
		this.pathPhoto = pathPhoto;
	}

	public int getNecessaryFunding() {
		return necessaryFunding;
	}

	public void setNecessaryFunding(int necessaryFunding) {
		this.necessaryFunding = necessaryFunding;
	}

	public int getAllocatedFunding() {
		return allocatedFunding;
	}

	public void setAllocatedFunding(int allocatedFunding) {
		this.allocatedFunding = allocatedFunding;
	}

	public int getVolunteerNeeded() {
		return volunteerNeeded;
	}

	public void setVolunteerNeeded(int volunteerNeeded) {
		this.volunteerNeeded = volunteerNeeded;
	}

	public int getVolunteerAllocated() {
		return volunteerAllocated;
	}

	public void setVolunteerAllocated(int volunteerAllocated) {
		this.volunteerAllocated = volunteerAllocated;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", address=" + address + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", description=" + description + ", pathPhoto=" + pathPhoto
				+ ", necessaryFunding=" + necessaryFunding + ", allocatedFunding=" + allocatedFunding
				+ ", volunteerNeeded=" + volunteerNeeded + ", volunteerAllocated=" + volunteerAllocated + ", statut="
				+ statut + ", category=" + category + ", partner=" + partner + ", event=" + event + "]";
	}

		
}
