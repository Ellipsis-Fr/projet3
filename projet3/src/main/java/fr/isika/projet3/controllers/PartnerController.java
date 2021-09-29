package fr.isika.projet3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import fr.isika.projet3.entities.Partner;
import fr.isika.projet3.services.IPartnerService;

@Controller
@Transactional
public class PartnerController {
	
	/**
	 * partner service
	 */
	@Autowired
	private IPartnerService partnerService;
	
	/**
	 * Connection partner
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/connexionPartner")
	public String PartnerForm(Model model) {
		model.addAttribute("partner", new Partner());
		return "connexionPartner";
	}

	/**
	 * Registration partner
	 * 
	 * @param partner
	 * @param model
	 * @return
	 */
	@PostMapping("/registrationPartner")
	public String registrationPartner(@ModelAttribute("partner") Partner partner, Model model) {

		partnerService.create(partner);
		
		model.addAttribute("partner", new Partner());
		
		return "connexionPartner";
	}
	
	/**
	 * connection partner
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
//	@PostMapping("/connexionPartner")
//	public ModelAndView add(@RequestParam("email") String email, @RequestParam("password") String password) {
//		
//		System.out.println("email : " + email + ", password : " + password);
//		
//		ModelAndView mv = new ModelAndView();
//		Partner partner = partnerService.partnerLogIn(email, password);
//		if (partner == null) {
//			mv.setViewName("connexionPartner");
//			mv.addObject("result", "email et/ou mot de passe non reconnu");
//			mv.addObject("partner", new Partner());
//		} else {
//			mv.setViewName("/WEB-INF/dashboardPartner/home");
//			mv.addObject("result", "tu es connecté");
//			mv.addObject("partner", partner);
//		}
//		return mv;
//	}
		
}
