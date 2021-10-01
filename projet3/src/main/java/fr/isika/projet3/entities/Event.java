package fr.isika.projet3.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import fr.isika.projet3.enumerations.TypeEvent;

@Entity
@Table(name="Events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private TypeEvent typeEvent;
	private String pathFolder;
	
	@OneToMany(mappedBy="event", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Activity> activities;
	
	@OneToMany(mappedBy="event", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Donation> donations;
	
	@Transient
	private int sumDonations;
	
//	@OneToMany(mappedBy="event", fetch = FetchType.EAGER)
//	@Fetch(value = FetchMode.SUBSELECT)
//	private List<Volunteer> volunteers;
//	
//	@OneToMany(mappedBy="event", fetch = FetchType.EAGER)
//	@Fetch(value = FetchMode.SUBSELECT)
//	private List<Participant> participants;
//	
//	@OneToMany(mappedBy="event", fetch = FetchType.EAGER)
//	@Fetch(value = FetchMode.SUBSELECT)
//	private List<Partner> partners;

	
	public Event() { 
		super();
		activities = new ArrayList<>();
		donations = new ArrayList<>();
		sumDonations = 0;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public TypeEvent getTypeEvent() {
		return typeEvent;
	}
	
	public void setTypeEvent(TypeEvent typeEvent) {
		this.typeEvent = typeEvent;
	}

	public String getPathFolder() {
		return pathFolder;
	}

	public void setPathFolder(String pathFolder) {
		this.pathFolder = pathFolder;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public int getSumDonations() {
		return sumDonations;
	}

	public void setSumDonations(int sumDonations) {
		this.sumDonations = sumDonations;
	}
	
//	public List<Volunteer> getVolunteers() {
//	return volunteers;
//}
//
//public void setVolunteers(List<Volunteer> volunteers) {
//	this.volunteers = volunteers;
//}
//
//public List<Participant> getParticipants() {
//	return participants;
//}
//
//public void setParticipants(List<Participant> participants) {
//	this.participants = participants;
//}
//
//public List<Partner> getPartners() {
//	return partners;
//}
//
//public void setPartners(List<Partner> partners) {
//	this.partners = partners;
//}
	
	public int getCompletionPercentage () {
		Instant instantStartDate = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Instant instantEndDate = endDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Instant instantToday = (LocalDate.now()).atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		return (int) (((double)instantToday.toEpochMilli() - (double)instantStartDate.toEpochMilli()) / ((double)instantEndDate.toEpochMilli() - (double)instantStartDate.toEpochMilli()) * 100);
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", typeEvent=" + typeEvent
				+ ", pathFolder=" + pathFolder + ", sumDonations=" + sumDonations + "]";
	}

}
