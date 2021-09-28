package fr.isika.projet3.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.isika.projet3.services.IAssociationService;
import fr.isika.projet3.services.IDonationService;
import fr.isika.projet3.services.IEventService;

public class DonationController { //Certainement à supprimer
	// keeps the information of the association's event visited
	private static final String ATT_SESSION_ASSOCIATION_EVENT_VISITED = "sessionAssociationVisited"; //Utiliser pour CRUD sur l'association en cours de visualisation
	private static final String ATT_SESSION_EVENT_EVENT_VISITED = "sessionEventVisited"; //Utiliser pour CRUD sur l'evenement en cours de visualisation
		
	@Autowired
	IEventService eventService;
	
	@Autowired
	IAssociationService associationService;
	
	@Autowired
	IDonationService donationService;
}
