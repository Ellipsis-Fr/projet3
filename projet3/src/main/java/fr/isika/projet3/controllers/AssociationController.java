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
	
	@PostMapping("/connexionAssociation")
	public String connexion(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpServletRequest req) {
		
		Association association = associationService.associationLogIn(email, password);
		
		if (association == null) {

			model.addAttribute("result", "email et/ou mot de passe non reconnu");
			model.addAttribute("association", new Association());
			
			return "connexionAssociation";
		} 
		
		HttpSession session = req.getSession();

		session.setAttribute("sessionAssociation", association);
		if (association.isEventInProgress()) session.setAttribute("sessionEvent", association.getEvent());

		return "redirect: dashboardAsso/home";
	}
	
	@RequestMapping("dashboardAsoo/logout")  
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
		
		return "/dashboardAsso/home";
	}
	
	@GetMapping("dashboardAsso/editAccount")
	public String editAccountForm(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute("sessionAssociation");
		
		model.addAttribute("association", association);
		
		return "editAccount";
	}
	
	@PostMapping("dashboardAsso/editAccount")
	public String editedAccount(@RequestParam MultipartFile logo, @ModelAttribute("association") Association associationUpdated, HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute("sessionAssociation");
		
		if (!logo.getOriginalFilename().isEmpty()) {
			associationService.deleteFile(association.getPathLogo());
			associationUpdated.setPathLogo(associationService.saveFile(logo, association.getPathFolder()));
		}
		
		association = associationService.updateByFields(associationUpdated, association);
		
		association = associationService.update(association);
		
		session.setAttribute("sessionAssociation", association);
		model.addAttribute("association", association);
		
		return "editAccount";
	}
	
	@GetMapping("dashboardAsso/deleteAccount")
	public String deleteAccount(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute("sessionAssociation");
		
		association = associationService.findOne(association.getId());
		associationService.deleteFolder(association.getPathFolder());
		associationService.delete(association);
		
		session.invalidate();
		
		return "redirect: ../index";
	}
}
