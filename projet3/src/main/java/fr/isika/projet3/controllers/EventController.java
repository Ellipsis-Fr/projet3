package fr.isika.projet3.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Association;
import fr.isika.projet3.entities.Donation;
import fr.isika.projet3.entities.Event;
import fr.isika.projet3.entities.User;
import fr.isika.projet3.entities.UserSociety;
import fr.isika.projet3.enumerations.TypeEvent;
import fr.isika.projet3.services.IAssociationService;
import fr.isika.projet3.services.IDonationService;
import fr.isika.projet3.services.IEventService;
import fr.isika.projet3.services.IUserService;
import fr.isika.projet3.services.IUserSocietyService;

@Controller
@Transactional
public class EventController {
	//TODO: A ajouter sur toutes les destinations des liens situés en en-tête et pied de page pour destruction session
	// keeps the association's connection information
	private static final String ATT_SESSION_ASSOCIATION = "sessionAssociation";
	private static final String ATT_SESSION_EVENT = "sessionEvent";
	
	// keeps the information of the association's event visited
	private static final String ATT_SESSION_ASSOCIATION_EVENT_VISITED = "sessionAssociationVisited"; //Utiliser pour CRUD sur l'association en cours de visualisation
	private static final String ATT_SESSION_EVENT_VISITED = "sessionEventVisited"; //Utiliser pour CRUD sur l'evenement en cours de visualisation
	
	private static final String FIELD_CHECK_TYPE_USER = "userType";
	private static final String FIELD_SIRET = "siret";
	private static final String FIELD_EMAIL = "email";
	
	@Autowired
	IEventService eventService;
	
	@Autowired
	IAssociationService associationService;
	
	@Autowired
	IDonationService donationService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IUserSocietyService userSocietyService;
	
	@PostMapping("dashboardAsso/createEvent")
	public String createEvent(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
		
		Event event = eventService.init(req);	
		event.setPathFolder(eventService.createNewFolder(association.getPathFolder()));
		eventService.create(event);
		
		association.setEvent(event);
		association.setEventInProgress(true);
		association = associationService.update(association);
		
		session.setAttribute(ATT_SESSION_ASSOCIATION, association);
		session.setAttribute(ATT_SESSION_EVENT, association.getEvent());
		
		return "redirect: home";
	}
	
	@GetMapping("dashboardAsso/closeEvent")
	public String closeEvent(HttpServletRequest req) {
		//TODO: reste : envoi mail, suppression tables et conservation des données utilisateurs
		HttpSession session = req.getSession();
		Event event = (Event) session.getAttribute(ATT_SESSION_EVENT);
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
		
		event = eventService.findOne(event.getId());
		eventService.deleteFolder(event.getPathFolder());
		eventService.delete(event);
		association.setEvent(null);
		association.setEventInProgress(false);
		association = associationService.update(association);
			
		session.setAttribute(ATT_SESSION_ASSOCIATION, association);
		session.removeAttribute(ATT_SESSION_EVENT);
		
		return "redirect: home";
	}
	
	@GetMapping("event")
	public String eventAccess(@RequestParam("id") String id, HttpServletRequest req) {
		//TODO: Remplacer la fonction de redirection vers l'index par un filtre menant à une page spécifique précisant qu'il n'y a plus d'évent
		HttpSession session = req.getSession();
		
		if (session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED) != null) { // TODO: Faire une comparaison entre l'associaiton en session et l'association obtenu avec l'id. si égaux alors cela signifie que nous venons d'une méthode/action initiée sur cet event_visited. Donc nous devons conserver les session (association_v, event_v, et compte utilisateur)
			session.invalidate();
			session = req.getSession();
		}
		
		Association association  = associationService.findOne(Long.parseLong(id));
		
		if (association == null) return "redirect: index";
		
		Event event = eventService.findOne(association.getEvent().getId());
		
		int sumDonations = getTotalDonationAmount(event);
		event.setSumDonations(sumDonations);
		
		session.setAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED, association);
		session.setAttribute(ATT_SESSION_EVENT_VISITED, event);

		return "event/home";
	}
	
	
	@PostMapping({"event/newDonation", "/newDonation", "newDonation"})
	public @ResponseBody String newDonation(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		
		// Reload ours objects save in session
		associationService.findOne(associationVisited.getId());
		eventService.findOne(eventVisited.getId());
		
		boolean isUserSociety = req.getParameter(FIELD_CHECK_TYPE_USER) != null;
		String siret = req.getParameter(FIELD_SIRET);
		String email = req.getParameter(FIELD_EMAIL);
		
		User user = getUser(associationVisited, isUserSociety, siret, email);
		
		if (user == null) user = newUser(req, associationVisited, isUserSociety);
		else user = updateUser(req, user, isUserSociety);
		
		Donation donation = donationService.init(req);
		donation.setUser(user);
		donation.setEvent(eventVisited);
		donationService.create(donation);
		
		eventVisited.setSumDonations(eventVisited.getSumDonations() + donation.getAmount());

		return eventVisited.getSumDonations() + "";
	}

	private User getUser(Association associationVisited, boolean isUserSociety, String siret, String email) {
		List<User> users = associationVisited.getUsers();
		
		if (isUserSociety) {
			for (User user : users) {
				if (user instanceof UserSociety) {
					UserSociety userSociety = (UserSociety) user;
					if (userSociety.getSiret().equals(siret)) return userSociety;
				}
			}
		} else {
			for (User user : users) {
				if (user instanceof UserSociety) continue;
				if (user.getEmail().equalsIgnoreCase(email)) return user;					
			}
		}
		
		return null;
	}
	
	private User newUser(HttpServletRequest req, Association associationVisited, boolean isUserSociety){
		if (isUserSociety) {
			UserSociety user = userSocietyService.init(req);
			user.setAssociation(associationVisited);
			userSocietyService.create(user);
			return user;
		} else {
			User user = userService.init(req);
			user.setAssociation(associationVisited);
			userService.create(user);
			return user;
		}
	}
	
	private User updateUser(HttpServletRequest req, User userInDataBase, boolean isUserSociety) {
		User userUpdated = null;
		
		if (isUserSociety) {
			userUpdated = userSocietyService.init(req);
			userUpdated = userSocietyService.updateByFields((UserSociety) userUpdated, (UserSociety) userInDataBase);
			userUpdated = userSocietyService.update((UserSociety) userUpdated);
		}
		else {
			userUpdated = userService.init(req);
			userUpdated = userService.updateByFields(userUpdated, userInDataBase);
			userUpdated = userService.update(userUpdated);
		}
		
		return userUpdated;
	}
	
	private int getTotalDonationAmount(Event eventVisited) {
		return eventVisited.getDonations().stream().map(x -> x.getAmount()).mapToInt(Integer::intValue).sum();
	}
	
	
	
	
	
	
	
	
	
	
	// ==== Mock Data
	@GetMapping("mockData")
	public String mockData() {
		List<Association> associations = new ArrayList<>();
		
		Association association1 = new Association();
		association1.setRna("W123456780");
		association1.setPathFolder(association1.getRna());
		association1.setName("Lutte Animale");
		association1.setAddress("10 rue Paul Couderc 92330 Sceaux");
		associations.add(association1);
		
		Association association2 = new Association();
		association2.setRna("W123456781");
		association2.setPathFolder(association2.getRna());
		association2.setName("Aide aux enfants");
		association2.setAddress("11 rue des Noyers 03410 Saint-Victor");
		associations.add(association2);
		
		Association association3 = new Association();
		association3.setRna("W123456782");
		association3.setPathFolder(association3.getRna());
		association3.setName("Personne Dehors");
		association3.setAddress("17 rue du Moulin Paillasson 42300 Roanne");
		associations.add(association3);
		
		Association association4 = new Association();
		association4.setRna("W123456783");
		association4.setPathFolder(association4.getRna());
		association4.setName("Accès à la connaissance");
		association4.setAddress("72 rue des Patriotes 02100 Saint-Quentin");
		associations.add(association4);
		
		Association association5 = new Association();
		association5.setRna("W123456784");
		association5.setPathFolder(association5.getRna());
		association5.setName("Une main tendue");
		association5.setAddress("11 rue des Peupliers 67370 Truchtersheim");
		associations.add(association5);
		
		Association association6 = new Association();
		association6.setRna("W123456785");
		association6.setPathFolder(association6.getRna());
		association6.setName("A bas le plastique");
		association6.setAddress("104 rue la Boétie 75008 Paris");
		associations.add(association6);
		
		Association association7 = new Association();
		association7.setRna("W123456786");
		association7.setPathFolder(association7.getRna());
		association7.setName("Le soin pour tous");
		association7.setAddress("17 rue du Neubourg 76500 Elbeuf");
		associations.add(association7);
		
		Association association8 = new Association();
		association8.setRna("W123456787");
		association8.setPathFolder(association8.getRna());
		association8.setName("Institut Abbé Pierre");
		association8.setAddress("8 rue Joseph Brenier 38200 Vienne");
		associations.add(association8);
		
		Association association9 = new Association();
		association9.setRna("W123456788");
		association9.setPathFolder(association9.getRna());
		association9.setName("Un plat, un toit");
		association9.setAddress("13 rue du Port Leyron 33420 Branne");
		associations.add(association9);
		
		Association association10 = new Association();
		association10.setRna("W123456789");
		association10.setPathFolder(association10.getRna());
		association10.setName("Un cartable et un stylo");
		association10.setAddress("54 rue Jules Sauzède 11000 Carcassonne");
		associations.add(association10);
		
		
		List<Event> events = new ArrayList<>();
		
		Event event1 = new Event();
		event1.setStartDate(LocalDate.parse("2021-08-02"));
		event1.setEndDate(LocalDate.parse("2021-10-09"));
		event1.setTypeEvent(TypeEvent.funndraisingAndActivities);
		events.add(event1);
		
		Event event2 = new Event();
		event2.setStartDate(LocalDate.parse("2021-09-01"));
		event2.setEndDate(LocalDate.parse("2021-12-16"));
		event2.setTypeEvent(TypeEvent.funndraisingAndActivities);
		events.add(event2);
		
		Event event3 = new Event();
		event3.setStartDate(LocalDate.parse("2021-07-01"));
		event3.setEndDate(LocalDate.parse("2021-11-12"));
		event3.setTypeEvent(TypeEvent.funndraisingAndActivities);
		events.add(event3);
		
		Event event4 = new Event();
		event4.setStartDate(LocalDate.parse("2021-10-01"));
		event4.setEndDate(LocalDate.parse("2021-12-12"));
		event4.setTypeEvent(TypeEvent.funndraisingAndActivities);
		events.add(event4);
		
		Event event5 = new Event();
		event5.setStartDate(LocalDate.parse("2021-09-01"));
		event5.setEndDate(LocalDate.parse("2022-01-10"));
		event5.setTypeEvent(TypeEvent.funndraisingAndActivities);
		events.add(event5);
		
		Event event6 = new Event();
		event6.setStartDate(LocalDate.parse("2021-09-05"));
		event6.setEndDate(LocalDate.parse("2021-10-09"));
		event6.setTypeEvent(TypeEvent.funndraisingAndActivities);
		events.add(event6);
		
		Event event7 = new Event();
		event7.setStartDate(LocalDate.parse("2021-05-01"));
		event7.setEndDate(LocalDate.parse("2021-10-10"));
		event7.setTypeEvent(TypeEvent.funndraisingAndActivities);
		events.add(event7);
		
		Event event8 = new Event();
		event8.setStartDate(LocalDate.parse("2021-04-06"));
		event8.setEndDate(LocalDate.parse("2021-11-21"));
		event8.setTypeEvent(TypeEvent.funndraisingAndActivities);
		events.add(event8);

		for (int i = 0; i < associations.size(); i++) {
			associations.get(i).setPathFolder(associationService.createNewFolder(associations.get(i).getRna()));

			if (i < 8) {
				events.get(i).setPathFolder(eventService.createNewFolder(associations.get(i).getPathFolder()));
				eventService.create(events.get(i));
				associations.get(i).setEvent(events.get(i));
				associations.get(i).setEventInProgress(true);
			}
			
			associations.get(i).setEmail("crespel.romain@gmail.com");
			associations.get(i).setPassword("1111111" + i);
			
			associationService.create(associations.get(i));
		}
				
		return "redirect: index";
	}
}
