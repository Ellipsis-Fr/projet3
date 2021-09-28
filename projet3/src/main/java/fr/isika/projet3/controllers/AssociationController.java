package fr.isika.projet3.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;

import fr.isika.projet3.entities.Association;
import fr.isika.projet3.entities.Event;
import fr.isika.projet3.services.IAssociationService;

@Controller
@Transactional
public class AssociationController {
	//TODO: Ajouter un session invalidate pour déconnecter l'association (ou le partenaire) en cas de saisie d'une URL hors zone de dashboard
	//TODO: Réussir à afficher texte d'erreur en cas de fichier trop gros
	// keeps the association's connection information
	private static final String ATT_SESSION_ASSOCIATION = "sessionAssociation";
	private static final String ATT_SESSION_EVENT = "sessionEvent";
	
	@Autowired
	IAssociationService associationService;
	
	@RequestMapping("/connexionAssociation")
	public String associationForm(Model model) {
		model.addAttribute("association", new Association());
		return "connexionAssociation";
	}

	@PostMapping("/registrationAssociation")
	public String registrationAssociation(@RequestParam MultipartFile logo, @ModelAttribute("association") Association association, Model model) {

		association.setPathFolder(associationService.createNewFolder(association.getRna()));
		association.setPathLogo(associationService.saveFile(logo, association.getPathFolder()));
		associationService.create(association);
		
		model.addAttribute("association", new Association()); //Réinitialise le formulaire
		
		return "connexionAssociation";
	}
	
	@PostMapping("/checkAssociationNotExists")
	public @ResponseBody String checkAssociationNotExists(@RequestParam("rna") String rna) {
		boolean isDouble = associationService.checkDouble(rna);
		return isDouble ? "exists" : "not exists";
	}
	
//	@PostMapping("/connexionAssociation")
//	public String connexion(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpServletRequest req) {
//		
//		Association association = associationService.associationLogIn(email, password);
//		
//		if (association == null) {
//
//			model.addAttribute("result", "email et/ou mot de passe non reconnu");
//			model.addAttribute("association", new Association());
//			
//			return "connexionAssociation";
//		} 
//		
//		HttpSession session = req.getSession();
//
//		session.setAttribute(ATT_SESSION_ASSOCIATION, association);
//		if (association.isEventInProgress()) session.setAttribute(ATT_SESSION_EVENT, association.getEvent());
//
//		return "redirect: dashboardAsso/home";
//	}
	
	@PostMapping("/checkEmailPasswordAssociation")
	public @ResponseBody String checkEmailPasswordAssociation(@RequestParam("email") String email, @RequestParam("password") String password) {
		System.out.println("dans controller " + email + " " + password);
		Association association = associationService.associationLogIn(email, password);
		
		if (association == null) {
			return "not Match";
		} else return "Match";
	}
	
	@PostMapping("/connexionAssociation")
	public String connexion(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest req) {
				
		Association association = associationService.associationLogIn(email, password);
		
		HttpSession session = req.getSession();
		
		session.setAttribute(ATT_SESSION_ASSOCIATION, association);
		if (association.isEventInProgress()) session.setAttribute(ATT_SESSION_EVENT, association.getEvent());
		
		return "redirect: dashboardAsso/home";
	}
	
	@RequestMapping({"dashboardAsso/logout", "logout"})  
    public String logoutPage(HttpServletRequest request) {  
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect: ../index"; 
     }  
	
	@RequestMapping({"/homeDashboardAssociation", "dashboardAsso/home","home"})
	public String homeDashboardAssociation(HttpServletRequest req) {
		HttpSession session = req.getSession();

		System.out.println("Session association " + session.getAttribute("sessionAssociation"));
		System.out.println("Session event " + session.getAttribute("sessionEvent"));
//		Event event2 = eventService.findOne(event.getId());

//		System.out.println(event2);
		return "/dashboardAsso/home";
	}
	
	@GetMapping("dashboardAsso/editAccount")
	public String editAccountForm(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
		
		model.addAttribute("association", association);
		
		return "editAccount";
	}
	
	@PostMapping("dashboardAsso/editAccount")
	public String editedAccount(@RequestParam MultipartFile logo, @ModelAttribute("association") Association associationUpdated, HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
		
		if (!logo.getOriginalFilename().isEmpty()) {
			if (!association.getPathLogo().isEmpty()) associationService.deleteFile(association.getPathLogo()); // The condition is necessary cause of the mock data where there isn't logo to be loaded. In case of inscription on the site an association couldn't be without logo
			associationUpdated.setPathLogo(associationService.saveFile(logo, association.getPathFolder()));
		}
		
		association = associationService.updateByFields(associationUpdated, association);
		
		association = associationService.update(association);
		
		session.setAttribute(ATT_SESSION_ASSOCIATION, association);
		model.addAttribute("association", association);
		
		return "editAccount";
	}
	
	@GetMapping("dashboardAsso/deleteAccount")
	public String deleteAccount(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
		
		association = associationService.findOne(association.getId());
		associationService.deleteFolder(association.getPathFolder());
		associationService.delete(association);
		
		session.invalidate();
		
		return "redirect: ../index";
	}
}
