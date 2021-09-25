package fr.isika.projet3.controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Association;
import fr.isika.projet3.entities.Event;
import fr.isika.projet3.services.IAssociationService;
import fr.isika.projet3.services.IEventService;

@Controller
@Transactional
public class EventController {

	@Autowired
	IEventService eventService;
	
	@Autowired
	IAssociationService associationService;
	
	@PostMapping("dashboardAsso/createEvent")
	public String createEvent(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute("sessionAssociation");
		
		Event event = eventService.init(req);	
		event.setPathFolder(eventService.createNewFolder(association.getPathFolder()));
		eventService.create(event);
		
		association.setEvent(event);
		association.setEventInProgress(true);
		association = associationService.update(association);
		
		session.setAttribute("sessionAssociation", association);
		session.setAttribute("sessionEvent", association.getEvent());
		
		return "redirect: home";
	}
	
	@GetMapping("dashboardAsso/closeEvent")
	public String closeEvent(HttpServletRequest req) {
		//TODO: reste énormément à faire
		HttpSession session = req.getSession();
		Event event = (Event) session.getAttribute("sessionEvent");
		Association association = (Association) session.getAttribute("sessionAssociation");
		
		event = eventService.findOne(event.getId());
		eventService.deleteFolder(event.getPathFolder());
		eventService.delete(event);
		association.setEvent(null);
		association.setEventInProgress(false);
		association = associationService.update(association);
			
		session.setAttribute("sessionAssociation", association);
		session.removeAttribute("sessionEvent");
		
		return "redirect: home";
//		return "/dashboardAsso/home";
	}
}
