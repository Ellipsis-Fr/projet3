package fr.isika.projet3.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.isika.projet3.enumerations.PaymentMethod;
import fr.isika.projet3.enumerations.Statut;

@Entity
@Table(name="Donations")
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int amount;
	private String amount2;
	private LocalDate date;
	private Statut statut;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Event event;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public Donation() {
		super();
	}

	public Donation(Long id, int amount, LocalDate date, Statut statut, User user, Event event) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.statut = statut;
		this.user = user;
		this.event = event;
	}

	public Donation(String amount2) {
		super();
		this.amount2 = amount2;
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", amount=" + amount + ", date=" + date + ", statut=" + statut + ", user=" + user
				+ ", event=" + event + "]";
	}
	
	
	
}
