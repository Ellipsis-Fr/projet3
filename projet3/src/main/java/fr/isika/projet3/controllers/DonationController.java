package fr.isika.projet3.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.isika.projet3.entities.Donation;
import fr.isika.projet3.entities.Event;
import fr.isika.projet3.services.IAssociationService;
import fr.isika.projet3.services.IDonationService;
import fr.isika.projet3.services.IEventService;

@Controller
@Transactional
public class DonationController { //Certainement à supprimer
	
	private static final String ATT_SESSION_EVENT = "sessionEvent"; //Utiliser pour CRUD sur l'evenement en cours de visualisation
		
	@Autowired
	IEventService eventService;
	
	@Autowired
	IAssociationService associationService;
	
	@Autowired
	IDonationService donationService;
					 
	@RequestMapping("dashboardAsso/listDonations")
	public String donationForm(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Event event = (Event) session.getAttribute(ATT_SESSION_EVENT);
		
		//reload object(event) ave in session
		event = eventService.findOne(event.getId());
		
		List<Donation> donations = event.getDonations();
		model.addAttribute("listDonations", donations);
		
		return "listDonations";
	}
}
