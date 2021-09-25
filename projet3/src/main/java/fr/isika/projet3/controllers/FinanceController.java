package fr.isika.projet3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fr.isika.projet3.entities.Finance;
import fr.isika.projet3.services.IFinanceService;

@Controller
@Transactional
public class FinanceController {
	
	@Autowired
	private IFinanceService iFinanceService;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/createFinance")
	public String financeForm(Model model) {
		model.addAttribute("finance", new Finance());
		return "createFinance";
	}
	
	/**
	 * 
	 * @param finance
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/registrationFinance", method = RequestMethod.POST)
	public String registrationFinance( @ModelAttribute("finance") Finance finance, Model model) {
		
		this.iFinanceService.create(finance);
		model.addAttribute("finance", new Finance()); 
		return "createFinance";
	}
	
}
