package fr.isika.projet3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.isika.projet3.entities.Association;
import fr.isika.projet3.services.IAssociationService;

@Controller
@Transactional
public class HomeController {

	@Autowired
	IAssociationService associationService;
	
	@RequestMapping("/index")
	public ModelAndView home() {
		//TODO: Remplacer par les collectes actives
		System.out.println("là");
		List<Association> associations = associationService.findAll();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("associations", associations);

		return mv;
	}
}
