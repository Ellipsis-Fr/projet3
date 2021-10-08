package fr.isika.projet3.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	
	@Column(length = 500)
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Participant> participants;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Volunteer> volunteers;
	
	public Activity() {
		super();
		participants = new ArrayList<>();
		volunteers = new ArrayList<>();
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

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public List<Volunteer> getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(List<Volunteer> volunteers) {
		this.volunteers = volunteers;
	}
	
	public double getProgression()
    {
    			
        double prog = 0;
        if (necessaryFunding == 0 && volunteerNeeded == 0)
        {
            return 0;
        }
        else if (volunteerNeeded == 0)
        {
            prog = (allocatedFunding * 1.0 / necessaryFunding) * 100;
            return prog;
        }
        else if (necessaryFunding == 0)
        {
            prog = (volunteerAllocated * 1.0 / volunteerNeeded) * 100;
            return prog;
        }
        else
        {
            prog = ((allocatedFunding * 1.0 / necessaryFunding) + (volunteerAllocated * 1.0 / volunteerNeeded)) * 50;
            return prog;
        }
    }
}
