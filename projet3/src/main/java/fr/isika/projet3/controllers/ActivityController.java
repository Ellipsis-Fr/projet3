package fr.isika.projet3.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.isika.projet3.entities.Activity;
import fr.isika.projet3.entities.Association;
import fr.isika.projet3.entities.Event;
import fr.isika.projet3.services.IActivityService;
import fr.isika.projet3.services.IEventService;

@Controller
@Transactional
public class ActivityController {
	private static final String ATT_SESSION_EVENT = "sessionEvent";
	
	@Autowired
	IActivityService activityService;
	
	@Autowired
	IEventService eventService;
	
	@RequestMapping("dashboardAsso/listActivities")
	public String activityForm(Model model) {
		model.addAttribute("activity", new Activity());
		
		return "listActivities";
	}
	
	@RequestMapping("dashboardAsso/createActivity")
	public String createActivity(Model model) {
		model.addAttribute("activity", new Activity());
		
		return "createActivity";
	}
	
	@PostMapping("dashboardAsso/registrationActivity")
	public String registrationActivity(@RequestParam MultipartFile photo, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Event event = (Event) session.getAttribute(ATT_SESSION_EVENT);
		
		System.out.println("event récupéré " + event);
		
		Activity activity = activityService.init(req);
		activity.setEvent(event);
		activity.setPathPhoto(activityService.saveFile(photo, event.getPathFolder()));
		activityService.create(activity);
		
		return "redirect: home";
	}
	
	
	@PostMapping("/checkActivityNotExists")
	public @ResponseBody String checkActivityNotExists(@RequestParam("name") String name) {
		boolean isDouble = activityService.checkDouble(name);
		return isDouble ? "exists" : "not exists";
	}
}

