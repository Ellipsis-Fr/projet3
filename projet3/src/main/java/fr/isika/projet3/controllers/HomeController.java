package fr.isika.projet3.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
	// list of associations with event in progress
	private static final String ATT_REQUEST_LIST_ASSOCIATIONS = "requestAssociations";

	@Autowired
	IAssociationService associationService;
	
	@RequestMapping("index")
	public String home(Model model) {

		List<Association> associations = associationService.findAll().stream().filter(x -> x.isEventInProgress()).sorted((x1, x2) -> x1.getEvent().getEndDate().compareTo(x2.getEvent().getEndDate())).collect(Collectors.toList());
		
		model.addAttribute(ATT_REQUEST_LIST_ASSOCIATIONS, associations);

		return "index";
	}
}
