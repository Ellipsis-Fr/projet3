package fr.isika.projet3.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
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
import org.springframework.web.servlet.ModelAndView;

import fr.isika.projet3.entities.Activity;
import fr.isika.projet3.entities.Association;
import fr.isika.projet3.entities.Document;
import fr.isika.projet3.entities.Donation;
import fr.isika.projet3.entities.Event;
import fr.isika.projet3.entities.IRole;
import fr.isika.projet3.entities.Mail;
import fr.isika.projet3.entities.Messaging;
import fr.isika.projet3.entities.Participant;
import fr.isika.projet3.entities.Partner;
import fr.isika.projet3.entities.User;
import fr.isika.projet3.entities.UserSociety;
import fr.isika.projet3.entities.Volunteer;
import fr.isika.projet3.enumerations.MessageType;
import fr.isika.projet3.enumerations.Statut;
import fr.isika.projet3.enumerations.TypeEvent;
import fr.isika.projet3.services.IActivityService;
import fr.isika.projet3.services.IAssociationService;
import fr.isika.projet3.services.IDocumentService;
import fr.isika.projet3.services.IDonationService;
import fr.isika.projet3.services.IEventService;
import fr.isika.projet3.services.IMailService;
import fr.isika.projet3.services.IMessagingService;
import fr.isika.projet3.services.IParticipantService;
import fr.isika.projet3.services.IPartnerService;
import fr.isika.projet3.services.IUserService;
import fr.isika.projet3.services.IUserSocietyService;
import fr.isika.projet3.services.IVolunteerService;

@Controller
@Transactional
public class EventController {
	//TODO: A ajouter sur toutes les destinations des liens situ??s en en-t??te et pied de page pour destruction session
	// keeps the association's connection information
	private static final String ATT_SESSION_ASSOCIATION = "sessionAssociation";
	private static final String ATT_SESSION_EVENT = "sessionEvent";
	
	// keeps the information of the association's event visited
	private static final String ATT_SESSION_ASSOCIATION_EVENT_VISITED = "sessionAssociationVisited"; //Utiliser pour CRUD sur l'association en cours de visualisation
	private static final String ATT_SESSION_EVENT_VISITED = "sessionEventVisited"; //Utiliser pour CRUD sur l'evenement en cours de visualisation
	
	private static final String ATT_REQUEST_EVENT_INPROGRESS_ACTIVITIES = "requestInprogressActivities";
	private static final String ATT_REQUEST_EVENT_VALID_ACTIVITIES= "requestValidActivities";
	
	private static final String ATT_SESSION_USER_LOGGED = "sessionUserLogged";
	private static final String ATT_SESSION_ROLE_LOGGED = "sessionRoleLogged";
	
	private static final String ATT_EVENT_VISITED_MSG = "msg";
	
	private static final String FIELD_CHECK_TYPE_USER = "userType";
	private static final String FIELD_SIRET = "siret";
	private static final String FIELD_EMAIL = "email";
	
	@Autowired
	IEventService eventService;
	
	@Autowired
	IDonationService donationService;
	
	@Autowired
	IVolunteerService volunteerService;
	
	@Autowired
	IPartnerService partnerService;
	
	@Autowired
	IParticipantService participantService;
	
	@Autowired
	IAssociationService associationService;
		
	@Autowired
	IUserService userService;
	
	@Autowired
	IUserSocietyService userSocietyService;
	
	@Autowired
	IDocumentService documentService;
	
	@Autowired
	IMessagingService messagingService;
	
	@Autowired
	IMailService mailService;
	
	@Autowired
	IActivityService activityService;
	
	@PostMapping("dashboardAsso/createEvent")
	public String createEvent(HttpServletRequest req) {
		//TODO: ajouter une v??rification si document de l'association n'a pas ??t?? param??tr??
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
		//TODO: reste : envoi mail, suppression tables et conservation des donn??es utilisateurs
		HttpSession session = req.getSession();
		Event event = (Event) session.getAttribute(ATT_SESSION_EVENT);
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
		
		association = associationService.findOne(association.getId());
		event = eventService.findOne(event.getId());
		
		deleteActivities(event);
		deleteRoles(association);
		deleteDonations(event);
		deleteUsersNotRecontactable(association);
		
		eventService.deleteFolder(event.getPathFolder());
		eventService.delete(event);
		association.setEvent(null);
		association.setEventInProgress(false);
		association = associationService.update(association);
			
		session.setAttribute(ATT_SESSION_ASSOCIATION, association);
		session.removeAttribute(ATT_SESSION_EVENT);
		
		return "redirect: home";
	}
	
	private void deleteActivities(Event event) {
		List<Activity> activities = event.getActivities();
		
		for (Activity activity : activities) {
			activityService.delete(activityService.findOne(activity.getId()));
		}
	}
	
	private void deleteRoles(Association association) {
		for (User user : association.getUsers()) {
			
			if (user instanceof UserSociety) {
				UserSociety userSociety = (UserSociety) user;
				
				if (userSociety.getPartner() != null) {
					partnerService.delete(partnerService.findOne(userSociety.getPartner().getId()));
					userSociety.setPartner(null);
					userSocietyService.update(userSociety);
				}

			} else if (user.getParticipant() != null) {
				participantService.delete(participantService.findOne(user.getParticipant().getId()));
				user.setParticipant(null);
				userService.update(user);
			}
			else if (user.getVolunteer() != null) {
				volunteerService.delete(volunteerService.findOne(user.getVolunteer().getId()));
				user.setVolunteer(null);
				userService.update(user);
			}
		}
	}
	
	private void deleteDonations(Event event) {
		List<Donation> donations = event.getDonations();
		
		for (Donation donation : donations) {
			donationService.delete(donationService.findOne(donation.getId()));
		}
	}
	
	private void deleteUsersNotRecontactable(Association association) {
		List<User> users = association.getUsers();
		
		
		for (User user : new ArrayList<User>(users)) {
			if (!user.isRecontactable()) {
				userService.delete(userService.findOne(user.getId()));
				users.remove(user);
			}
		}
	}
	
	@GetMapping("event")
	public String eventAccess(@RequestParam("id") String id, HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
		
		Association association  = associationService.findOne(Long.parseLong(id));
		
		if (association == null) { //TODO: Remplacer la fonction de redirection vers l'index par un filtre menant ?? une page sp??cifique pr??cisant qu'il n'y a plus d'??vent
			session.invalidate();
			return "redirect: index";
		}
		
		Association associationEventVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		
		if (associationEventVisited != null && associationEventVisited.getId() != Long.parseLong(id)) { 
			// TODO: Faire une comparaison entre l'associaiton en session et l'association obtenu avec l'id. si ??gaux alors cela signifie que nous venons d'une m??thode/action initi??e sur cet event_visited. Donc nous devons conserver les session (association_v, event_v, et compte utilisateur)
			session.invalidate();
			session = req.getSession();
		}
		
		if (req.getParameter(ATT_EVENT_VISITED_MSG) != null) model.addAttribute(ATT_EVENT_VISITED_MSG, req.getParameter(ATT_EVENT_VISITED_MSG));


		Event event = eventService.findOne(association.getEvent().getId());

		event.setSumDonations(getTotalDonationAmount(event));
		
		List<Activity> inprogressActivities = event.getActivities().stream().filter(x -> x.getStatut() == Statut.IN_PROGRESS).collect(Collectors.toList());
		List<Activity> validActivities = event.getActivities().stream().filter(x -> x.getStatut() == Statut.VALID).collect(Collectors.toList());
		
		session.setAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED, association);
		session.setAttribute(ATT_SESSION_EVENT_VISITED, event);
		
		req.setAttribute(ATT_REQUEST_EVENT_INPROGRESS_ACTIVITIES, inprogressActivities);
		req.setAttribute(ATT_REQUEST_EVENT_VALID_ACTIVITIES, validActivities);

		return "event/home";
	}
	
	
	@PostMapping("newDonation")
	public @ResponseBody String newDonation(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		
		// Reload ours objects save in session
		associationVisited = associationService.findOne(associationVisited.getId());
		eventVisited = eventService.findOne(eventVisited.getId());
		
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
		
		// 1. m??thode avec taxReceiptService nomm??e saveFile (s'inspirer de saveFile de la classe activityService) ?? qui transmettre user et donation
		//	-> dans un premier temps cr??e le pdf
		//	-> ensuite enregistrer ce pdf dnas le dossier ServerContent/taxreceipt
		//	-> initialis?? et retourner l'objet taxReceipt (year, pathFile, emailuser)
		//
		// 2. Envoyer le re??u ?? l'utilisateur avec sendEmailService
		//
		// 3. Persister l'objet tax receipt
		//
		int sumDonations = getTotalDonationAmount(eventVisited);
		eventVisited.setSumDonations(sumDonations + donation.getAmount());

		return eventVisited.getSumDonations() + "";
	}
	
	@PostMapping("newVolunteer")
	public @ResponseBody String newVolunteer(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		
		// Reload ours objects save in session
		associationVisited = associationService.findOne(associationVisited.getId());
		eventVisited = eventService.findOne(eventVisited.getId());

		String email = req.getParameter(FIELD_EMAIL);
		
		User user = getUser(associationVisited, false, null, email);
		
		if (user == null) user = newUser(req, associationVisited, false);
		else {
			String role = isAlreadyEngaged(user);
			if (role != null) return role;
			
			user = updateUser(req, user, false);
		}
		
		Volunteer volunteer = volunteerService.init(req);
		volunteerService.create(volunteer);
		user.setVolunteer(volunteer);
		userService.update(user);		
		
		return "Inscription B??n??vole confirm??";
	}
	
	@PostMapping("newParticipant")
	public @ResponseBody String newParticipant(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		
		// Reload ours objects save in session
		associationVisited = associationService.findOne(associationVisited.getId());
		eventVisited = eventService.findOne(eventVisited.getId());

		String email = req.getParameter(FIELD_EMAIL);
		
		User user = getUser(associationVisited, false, null, email);
		
		if (user == null) user = newUser(req, associationVisited, false);
		else {
			String role = isAlreadyEngaged(user);
			if (role != null) return role;
			
			user = updateUser(req, user, false);
		}
		
		Participant participant = participantService.init(req);
		participantService.create(participant);
		user.setParticipant(participant);
		userService.update(user);		
		
		return "Inscription Participant confirm??";
	}
	
	@PostMapping("newPartner")
	public @ResponseBody String newPartner(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		
		// Reload ours objects save in session
		associationVisited = associationService.findOne(associationVisited.getId());
		eventVisited = eventService.findOne(eventVisited.getId());

		String siret = req.getParameter(FIELD_SIRET);
		
		UserSociety user = (UserSociety) getUser(associationVisited, true, siret, null);
		
		if (user == null) user = (UserSociety) newUser(req, associationVisited, true);
		else {
			String role = isAlreadyEngaged(user);
			if (role != null) return role;
			
			user = (UserSociety) updateUser(req, user, true);
		}
		
		Partner partner = partnerService.init(req);
		partnerService.create(partner);
		user.setPartner(partner);
		userService.update(user);		
		
		return "Inscription Partenaire confirm??";
	}
	
	private String isAlreadyEngaged(User user) {
		
		if (user instanceof UserSociety) {
			UserSociety userSociety = (UserSociety) user;
			if (userSociety.getPartner() != null) return "Compte Partenaire existant.";
		} else {
			if (user.getVolunteer() != null) return "Compte B??n??vole existant.";
			if (user.getParticipant() != null) return "Compte Participant existant.";
		}
		
		return null;
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
	
	private User getUser(Association associationVisited, String email) {
		List<User> users = associationVisited.getUsers();
		
		for (User user : users) {
			if (user.getEmail().equalsIgnoreCase(email)) return user;					
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

	@PostMapping("connexion")
	public ModelAndView connexionUser(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("role") String role, HttpServletRequest req, Model model) {		
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		
		// Reload ours objects save in session
		associationVisited = associationService.findOne(associationVisited.getId());
		eventVisited = eventService.findOne(eventVisited.getId());
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect: event?id=" + associationVisited.getId());
		
		User user = getUser(associationVisited, email);
		
		if (user == null || checkEmailPassword(user, password, role).equals("not Match")) {
//			model.addAttribute("connexionUser", "Email et/ou Mot de passe incorrect(s)");
//			return "event/home";
//			return "redirect: event?id=" + associationVisited.getId() + "connexionUser=Email+et%2Fou+Mot+de+passe+incorrect%28s%29";
			mv.addObject(ATT_EVENT_VISITED_MSG, "Email et/ou Mot de passe incorrect(s)");
			return mv;
		}

		switch(role) {
			case "volunteer":
				Volunteer volunteer = user.getVolunteer();
				session.setAttribute(ATT_SESSION_USER_LOGGED, user);
				session.setAttribute(ATT_SESSION_ROLE_LOGGED, volunteer);
				break;
			case "participant":
				Participant participant = user.getParticipant();
				session.setAttribute(ATT_SESSION_USER_LOGGED, user);
				session.setAttribute(ATT_SESSION_ROLE_LOGGED, participant);
				break;
			case "partner":
				UserSociety userSociety = (UserSociety) user;
				Partner partner = userSociety.getPartner();
				
				if (partner.getStatut() == Statut.PENDING) {
//					model.addAttribute("connexionUser", "Compte en attente de Validation Administrateur");
//					return "event/home";
					
					mv.addObject(ATT_EVENT_VISITED_MSG, "Compte en attente de Validation Administrateur");
					return mv;
				}
				
				session.setAttribute(ATT_SESSION_USER_LOGGED, userSociety);
				session.setAttribute(ATT_SESSION_ROLE_LOGGED, partner);
		}
		
//		model.addAttribute("connexionUser", "Connexion R??ussie !");
//		return "event/home";
		
		mv.addObject(ATT_EVENT_VISITED_MSG, "Connexion R??ussie !");
		return mv;
	}
	
	private String checkEmailPassword(User user, String password, String role) {
		
		switch(role) {
			case "volunteer":
				Volunteer volunteer = user.getVolunteer();
				if (volunteer == null) return "not Match";
				return volunteer.getPassword().equals(password) ? "Match" : "not Match";
			case "participant":
				Participant participant = user.getParticipant();
				if (participant == null) return "not Match";
				return participant.getPassword().equals(password) ? "Match" : "not Match";
			case "partner":
				UserSociety userSociety = null;
				
				try {
					userSociety = (UserSociety) user;
				} catch (ClassCastException e) {
					break;
				}
				
				Partner partner = userSociety.getPartner();
				if (partner == null) return "not Match";
				return partner.getPassword().equals(password) ? "Match" : "not Match";	
		}
		
		return "not Match";
	}
	
	@PostMapping("newActivity")
	public @ResponseBody String newActivity(@RequestParam MultipartFile photo, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		Partner partner = (Partner) session.getAttribute(ATT_SESSION_ROLE_LOGGED);
		
		// Reload ours objects save in session
		associationVisited = associationService.findOne(associationVisited.getId());
		eventVisited = eventService.findOne(eventVisited.getId());
		partner = partnerService.findOne(partner.getId());
		
		Activity activity = activityService.init(req);
		activity.setStatut(Statut.PENDING);
		activity.setPartner(partner);
		activity.setEvent(eventVisited);
		activity.setPathPhoto(activityService.saveFile(photo, eventVisited.getPathFolder()));
		activityService.create(activity);
		
		return "Proposition d'Activit?? envoy??e.";
	}
	
	@PostMapping("newMessage")
	public @ResponseBody String newMessage(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
				
		// Reload ours objects save in session
		associationVisited = associationService.findOne(associationVisited.getId());
		eventVisited = eventService.findOne(eventVisited.getId());

		Mail message = mailService.init(req);
		message.setRecipient(associationVisited.getName());
		message.setMessaging(associationVisited.getMessaging());
		mailService.create(message);
		
		return "Message envoy??.";
	}
	
	@GetMapping("subscribeActivity")	
	public ModelAndView subscribeActivity(@RequestParam("id") String id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect: event?id=" + associationVisited.getId());
		
		//TODO : v??rifier que la personne ne soit pas d??j?? inscrite ?? l'activit?? en question
		Activity activity = activityService.findOne(Long.parseLong(id));
		
		IRole role = (IRole) session.getAttribute(ATT_SESSION_ROLE_LOGGED);
		Volunteer volunteer = null;
		Participant participant = null;
		
		if (eventVisited == null || role == null) {
			mv.setViewName("redirect: index");
			return mv;
			
		} else if (role instanceof Volunteer) {
			volunteer = (Volunteer) role;
			
			volunteer.getActivities().add(activity);
			activity.getVolunteers().add(volunteer);
			activity.setVolunteerAllocated(activity.getVolunteerAllocated() + 1);
			
			volunteer = volunteerService.update(volunteer);
			
			session.setAttribute(ATT_SESSION_ROLE_LOGGED, volunteer);
			
		} else {
			participant = (Participant) role;
			
			participant.getActivities().add(activity);
			activity.getParticipants().add(participant);
			
			participant = participantService.update(participant);
			
			session.setAttribute(ATT_SESSION_ROLE_LOGGED, participant);
		}

		activityService.update(activity);
		
		mv.addObject(ATT_EVENT_VISITED_MSG, "Inscription enregistr??e.");
				
		return mv;
	}
	
	@GetMapping("unsubscribeActivity")	
	public ModelAndView unsubscribeActivity(@RequestParam("id") String id, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association associationVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		Event eventVisited = (Event) session.getAttribute(ATT_SESSION_EVENT_VISITED);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect: event?id=" + associationVisited.getId());
		
		//TODO : v??rifier que la personne ne soit pas d??j?? inscrite ?? l'activit?? en question
		Activity activity = activityService.findOne(Long.parseLong(id));
		
		IRole role = (IRole) session.getAttribute(ATT_SESSION_ROLE_LOGGED);
		Volunteer volunteer = null;
		Participant participant = null;
		
		if (eventVisited == null || role == null) {
			mv.setViewName("redirect: index");
			return mv;
			
		} else if (role instanceof Volunteer) {
			volunteer = (Volunteer) role;
			
			volunteer = volunteerService.findOne(volunteer.getId());
			
			volunteer.getActivities().remove(activity);
			activity.getVolunteers().remove(volunteer);
			activity.setVolunteerAllocated(activity.getVolunteerAllocated() - 1);
			
			volunteer = volunteerService.update(volunteer);
			
			session.setAttribute(ATT_SESSION_ROLE_LOGGED, volunteer);
			
		} else {
			participant = (Participant) role;
			
			participant = participantService.findOne(participant.getId());
			
			List<Activity> participant_activities = participant.getActivities();
			participant_activities.remove(activity);
			participant.setActivities(participant_activities);
			
			List<Participant> activity_participants = activity.getParticipants();
			activity_participants.remove(participant);
			activity.setParticipants(activity_participants);
			
			participant = participantService.update(participant);
			
			session.setAttribute(ATT_SESSION_ROLE_LOGGED, participant);
		}

		activityService.update(activity);
		
		mv.addObject(ATT_EVENT_VISITED_MSG, "D??sinscription enregistr??e.");
				
		return mv;
	}
	
	@PostMapping("getActivityToFinance")
	public @ResponseBody String getActivityToFinance(@RequestParam("id") String id) {
		Activity activity = activityService.findOne(Long.parseLong("id"));
		
		String informations = activity.getId()  + "%-%" + activity.getName()  + "%-%" + (activity.getNecessaryFunding() - activity.getAllocatedFunding());
		
		return informations;
	}
	
	@PostMapping("createFinanceActivity")
	public ModelAndView createFinance(@RequestParam("activityId") String activityId, @RequestParam("funding") int funding, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		Association associationEventVisited = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect: event?id=" + associationEventVisited.getId());
		
		Activity activity = activityService.findOne(Long.parseLong(activityId));
		activity.setAllocatedFunding(activity.getAllocatedFunding() + funding);
		activityService.update(activity);
		
		mv.addObject(ATT_EVENT_VISITED_MSG, "Financement enregistr??. Merci pour votre soutien");
		return mv;
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
		association4.setName("Acc??s ?? la connaissance");
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
		association6.setAddress("104 rue la Bo??tie 75008 Paris");
		associations.add(association6);
		
		Association association7 = new Association();
		association7.setRna("W123456786");
		association7.setPathFolder(association7.getRna());
		association7.setName("Le soin pour tous");
		association7.setAddress("rue du Neubourg 76500 Elbeuf");
		associations.add(association7);
		
		Association association8 = new Association();
		association8.setRna("W123456787");
		association8.setPathFolder(association8.getRna());
		association8.setName("Institut Abb?? Pierre");
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
		association10.setAddress("54 rue Jules Sauz??de 11000 Carcassonne");
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
		
		
		List<Activity> activities = new ArrayList<>(); //ADD from here to redirect
		
		Activity activity1 = new Activity();
		activity1.setStartDate(LocalDate.parse("2021-07-11"));
		activity1.setEndDate(LocalDate.parse("2021-12-10"));
		activity1.setName("L'activit?? cartabli??re");
		activity1.setAddress("11 rue de julien 21001 Julienville");
		activity1.setStatut(Statut.VALID);
		activity1.setEvent(event1);
		activities.add(activity1);
		
		
		Activity activity2 = new Activity();
		activity2.setStartDate(LocalDate.parse("2021-07-12"));
		activity2.setEndDate(LocalDate.parse("2021-12-11"));
		activity2.setName("L'activit?? dansi??re");
		activity2.setAddress("11 rue de la danse 21002 danseville");
		activity2.setStatut(Statut.VALID);
		activity2.setEvent(event1);
		activities.add(activity2);
	
		
		Activity activity3 = new Activity();
		activity3.setStartDate(LocalDate.parse("2021-07-13"));
		activity3.setEndDate(LocalDate.parse("2021-12-12"));
		activity3.setName("L'activit?? du soin");
		activity3.setAddress("11 rue de soin 21003 Soinville");
		activity3.setStatut(Statut.VALID);
		activity3.setEvent(event1);
		activities.add(activity3);
			
		
		Activity activity4 = new Activity();
		activity4.setStartDate(LocalDate.parse("2021-07-14"));
		activity4.setEndDate(LocalDate.parse("2021-12-13"));
		activity4.setName("L'activit?? m??nag??re");
		activity4.setAddress("11 rue du m??nage 21004 M??nageville");
		activity4.setStatut(Statut.VALID);
		activity4.setEvent(event1);
		activities.add(activity4);	

		
		Activity activity5 = new Activity();
		activity5.setStartDate(LocalDate.parse("2021-07-15"));
		activity5.setEndDate(LocalDate.parse("2021-12-14"));
		activity5.setName("L'activit?? nanardesque");
		activity5.setAddress("11 rue du nanar 21005 Nanarville");
		activity5.setStatut(Statut.VALID);
		activity5.setEvent(event1);
		activities.add(activity5);	
		
		
		Activity activity6 = new Activity();
		activity6.setStartDate(LocalDate.parse("2021-07-11"));
		activity6.setEndDate(LocalDate.parse("2021-12-10"));
		activity6.setName("L'activit?? cartabli??re");
		activity6.setAddress("11 rue de julien 21001 Julienville");
		activity6.setStatut(Statut.IN_PROGRESS);
		activity6.setEvent(event1);
		activities.add(activity6);
		
		
		Activity activity7 = new Activity();
		activity7.setStartDate(LocalDate.parse("2021-07-12"));
		activity7.setEndDate(LocalDate.parse("2021-12-11"));
		activity7.setName("L'activit?? dansi??re");
		activity7.setAddress("11 rue de la danse 21002 danseville");
		activity7.setStatut(Statut.IN_PROGRESS);
		activity7.setEvent(event1);
		activities.add(activity7);
	
		
		Activity activity8 = new Activity();
		activity8.setStartDate(LocalDate.parse("2021-07-13"));
		activity8.setEndDate(LocalDate.parse("2021-12-12"));
		activity8.setName("L'activit?? du soin");
		activity8.setAddress("11 rue de soin 21003 Soinville");
		activity8.setStatut(Statut.IN_PROGRESS);
		activity8.setEvent(event1);
		activities.add(activity8);
			
		
		Activity activity9 = new Activity();
		activity9.setStartDate(LocalDate.parse("2021-07-14"));
		activity9.setEndDate(LocalDate.parse("2021-12-13"));
		activity9.setName("L'activit?? m??nag??re");
		activity9.setAddress("11 rue du m??nage 21004 M??nageville");
		activity9.setStatut(Statut.IN_PROGRESS);
		activity9.setEvent(event1);
		activities.add(activity9);	

		
		Activity activity10 = new Activity();
		activity10.setStartDate(LocalDate.parse("2021-07-15"));
		activity10.setEndDate(LocalDate.parse("2021-12-14"));
		activity10.setName("L'activit?? nanardesque");
		activity10.setAddress("11 rue du nanar 21005 Nanarville");
		activity10.setStatut(Statut.IN_PROGRESS);
		activity10.setEvent(event1);
		activities.add(activity10);
		
		


		for (int i = 0; i < associations.size(); i++) {
			Association association = associations.get(i);
			association.setPathFolder(associationService.createNewFolder(associations.get(i).getRna()));
			
			Document document = new Document();		
			document.setPathFolder(documentService.createNewFolder(associations.get(i).getPathFolder(), "home"));
			document.setPathFolderPhoto(documentService.createNewFolder(document.getPathFolder(), "photos"));
			documentService.create(document);
			associations.get(i).setDocument(document);
			
			Messaging messaging = new Messaging();
			messaging.setPathFolder(messagingService.createNewFolder(associations.get(i).getPathFolder(), "messaging"));
			messagingService.create(messaging);
			associations.get(i).setMessaging(messaging);

			if (i < 8) {
				events.get(i).setPathFolder(eventService.createNewFolder(associations.get(i).getPathFolder()));
				eventService.create(events.get(i));
				associations.get(i).setEvent(events.get(i));
				associations.get(i).setEventInProgress(true);
				
				if (i == 1) {
					for (Activity activity : activities) {
						activity.setEvent(events.get(i));
						activityService.create(activity);
					}
				}
			}
			
			associations.get(i).setEmail("crespel.romain@gmail.com");
			associations.get(i).setPassword("1111111" + i);
			
			associationService.create(association);
		}
		

				
		return "redirect: index";
	}
//		for (int i = 0; i < associations.size(); i++) {
//			associations.get(i).setPathFolder(associationService.createNewFolder(associations.get(i).getRna()));
//			
//			Document document = new Document();		
//			document.setPathFolder(documentService.createNewFolder(associations.get(i).getPathFolder(), "home"));
//			document.setPathFolderPhoto(documentService.createNewFolder(document.getPathFolder(), "photos"));
//			documentService.create(document);
//			associations.get(i).setDocument(document);
//			
//			Messaging messaging = new Messaging();
//			messaging.setPathFolder(messagingService.createNewFolder(associations.get(i).getPathFolder(), "messaging"));
//			messagingService.create(messaging);
//			associations.get(i).setMessaging(messaging);
//
//			if (i < 8) {
//				events.get(i).setPathFolder(eventService.createNewFolder(associations.get(i).getPathFolder()));
//				eventService.create(events.get(i));
//				associations.get(i).setEvent(events.get(i));
//				associations.get(i).setEventInProgress(true);
//			}
//			
//			associations.get(i).setEmail("crespel.romain@gmail.com");
//			associations.get(i).setPassword("1111111" + i);
//			
//			associationService.create(associations.get(i));
//		}
//				
//		return "redirect: index";
//	}
}
