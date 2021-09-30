package fr.isika.projet3.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.projet3.entities.Association;
import fr.isika.projet3.services.IAssociationService;

@Controller
@Transactional
public class HomeController {
	// Used to verify if a session is in progress
	private static final String ATT_SESSION_ASSOCIATION = "sessionAssociation";
	private static final String ATT_SESSION_ASSOCIATION_EVENT_VISITED = "sessionAssociationVisited";
	
	// list of associations with event in progress
	private static final String ATT_REQUEST_LIST_ASSOCIATIONS = "requestAssociations";

	@Autowired
	IAssociationService associationService;
	
	@RequestMapping("index")
	public String home(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute(ATT_SESSION_ASSOCIATION) != null || session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED) != null) {
			session.invalidate();
		}

		List<Association> associations = associationService.findAll().stream().filter(x -> x.isEventInProgress()).sorted((x1, x2) -> x1.getEvent().getEndDate().compareTo(x2.getEvent().getEndDate())).collect(Collectors.toList());
		
		model.addAttribute(ATT_REQUEST_LIST_ASSOCIATIONS, associations);

		return "index";
	}
}
