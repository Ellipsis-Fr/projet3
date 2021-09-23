package fr.isika.projet3.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;

import fr.isika.projet3.entities.Association;
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
	
	// Fake connexion
	@PostMapping("/connexionAssociation")
	public ModelAndView add(@RequestParam("email") String email, @RequestParam("password") String password) {
		
		System.out.println("email : " + email + ", password : " + password);
		
		ModelAndView mv = new ModelAndView();
		Association association = associationService.associationLogIn(email, password);
		
		if (association == null) {
			mv.setViewName("connexionAssociation");
			mv.addObject("result", "email et/ou mot de passe non reconnu");
			mv.addObject("association", new Association());
		} else {
			mv.setViewName("/WEB-INF/dashboardAsso/home");
			mv.addObject("result", "tu es connecté");
			mv.addObject("association", association);
		}
		
		

		
		
		return mv;
	}
	
}
