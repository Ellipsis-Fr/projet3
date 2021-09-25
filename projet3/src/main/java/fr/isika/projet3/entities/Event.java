package fr.isika.projet3.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	public Event() {
		super();
	}

	public Event(LocalDate startDate, LocalDate endDate, TypeEvent typeEvent) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeEvent = typeEvent;
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

	@Override
	public String toString() {
		return "Event [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", typeEvent=" + typeEvent
				+ ", pathFolder=" + pathFolder + "]";
	}

	
}
