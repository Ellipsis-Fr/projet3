package fr.isika.projet3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	@RequestMapping(value="/registrationFinance", method = RequestMethod.POST)
	public String registrationFinance( @ModelAttribute("finance") Finance finance, Model model) {
		Double amountTotalInDB = 0.0;
		Double amountTotale =0.0;
		List<Finance> finances = iFinanceService.findAll();
		if(finances != null && finances.size() > 0) {
			for(Finance result : finances){
				if(result.getAmount() != null) {
					amountTotalInDB+=result.getAmount();
				}
			}
		}
		amountTotale=amountTotalInDB+finance.getAmount();
		if(amountTotale > 50000) {
			model.addAttribute("result", "Somme limite atteinte");
		} else {
			this.iFinanceService.create(finance);
			model.addAttribute("finance", new Finance());
			
		}
		return "createFinance";
	}
	
	/**
	 * Récupère la liste de finance
	 * 
	 * @return
	 */
	@RequestMapping(value="/getAllFinance", method = RequestMethod.GET, consumes = { "application/json" }) //TODO: ici
	public @ResponseBody ResponseEntity<?> getAllFinance() {
		return ResponseEntity.ok(this.iFinanceService.findAll());
	} 
	
}
