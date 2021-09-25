package fr.isika.projet3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Activity;
import fr.isika.projet3.services.IActivityService;

@Controller
@Transactional
public class ActivityController {

	@Autowired
	IActivityService activityService;
	
	@RequestMapping("/viewActivity")
	public String activityForm(Model model) {
		model.addAttribute("activity", new Activity());
		
		return "viewActivity";
	}
	/*
	@PostMapping("/registrationActivity")
	public String registrationActivity(@RequestParam MultipartFile photo, @ModelAttribute("activity") Activity activity, Model model) {
		
		activity.setPathFolder(activityService.createNewFolder(activity.getName()));
		activity.setPathPhoto(activityService.saveFile(photo, activity.getPathFolder()));
		activityService.create(activity);
		
		model.addAttribute("activity", new Activity());
		
		return "registrationActivity";
	}
	*/
	@PostMapping("/checkActivityNotExists")
	public @ResponseBody String checkActivityNotExists(@RequestParam("name") String name) {
		boolean isDouble = activityService.checkDouble(name);
		return isDouble ? "exists" : "not exists";
	}
}
