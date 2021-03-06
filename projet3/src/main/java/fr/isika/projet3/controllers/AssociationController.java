package fr.isika.projet3.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import fr.isika.projet3.entities.Document;
import fr.isika.projet3.entities.Event;
import fr.isika.projet3.entities.Mail;
import fr.isika.projet3.entities.Messaging;
import fr.isika.projet3.entities.Photo;
import fr.isika.projet3.entities.User;
import fr.isika.projet3.entities.UserSociety;
import fr.isika.projet3.enumerations.MessageType;
import fr.isika.projet3.enumerations.Statut;
import fr.isika.projet3.services.IAssociationService;
import fr.isika.projet3.services.IDocumentService;
import fr.isika.projet3.services.IMailService;
import fr.isika.projet3.services.IMessagingService;
import fr.isika.projet3.services.IPhotoService;
import fr.isika.projet3.services.ISendMailService;
import fr.isika.projet3.services.IUserService;

@Controller
@Transactional
public class AssociationController {
	//TODO: R??ussir ?? afficher texte d'erreur en cas de fichier trop gros
	// keeps the association's connection information
	private static final String ATT_SESSION_ASSOCIATION = "sessionAssociation";
	private static final String ATT_SESSION_EVENT = "sessionEvent";
	
	private static final String ATT_SESSION_ASSOCIATION_EVENT_VISITED = "sessionAssociationVisited";
	
	private static final String ATT_REQUEST_DOCUMENT = "requestDocument";
	private static final String ATT_REQUEST_MAILS = "requestMails";
	
	@Autowired
	IAssociationService associationService;
	
	@Autowired
	ISendMailService sendMailService;
	
	@Autowired
	IDocumentService documentService;
	
	@Autowired
	IPhotoService photoService;
	
	@Autowired
	IMessagingService messagingService;
	
	@Autowired
	IMailService mailService;
	
	@Autowired
	IUserService userService;
	
	@RequestMapping("/connexionAssociation")
	public String associationForm(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		if (session.getAttribute(ATT_SESSION_ASSOCIATION) != null || session.getAttribute(ATT_SESSION_ASSOCIATION_EVENT_VISITED) != null) {
			session.invalidate();
		}
		
		model.addAttribute("association", new Association());
		return "connexionAssociation";
	}

	@PostMapping("/registrationAssociation")
	public String registrationAssociation(@RequestParam MultipartFile logo, @ModelAttribute("association") Association association, Model model) {

		association.setPathFolder(associationService.createNewFolder(association.getRna()));
		association.setPathLogo(associationService.saveFile(logo, association.getPathFolder()));
		
		Document document = new Document();		
		document.setPathFolder(documentService.createNewFolder(association.getPathFolder(), "home"));
		document.setPathFolderPhoto(documentService.createNewFolder(document.getPathFolder(), "photos"));
		documentService.create(document);
		association.setDocument(document);
		
		Messaging messaging = new Messaging();
		messaging.setPathFolder(messagingService.createNewFolder(association.getPathFolder(), "messaging"));
		messagingService.create(messaging);
		association.setMessaging(messaging);
		
		associationService.create(association);
		
		model.addAttribute("association", new Association()); //R??initialise le formulaire
		
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
		Association association = associationService.logIn(email, password);
		
		if (association == null) {
			return "not Match";
		} else return "Match";
	}
	
	@PostMapping("/connexionAssociation")
	public String connexion(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest req) {
				
		Association association = associationService.logIn(email, password);
		if (association == null) return "connexionAssociation";
		
		HttpSession session = req.getSession();
		
		session.setAttribute(ATT_SESSION_ASSOCIATION, association);
		if (association.isEventInProgress()) session.setAttribute(ATT_SESSION_EVENT, association.getEvent());
		
		return "redirect: dashboardAsso/home";
	}
	
	@PostMapping("forgotPassword")
	public @ResponseBody String forgotPassword(@RequestParam("email") String email) {
		Association association = associationService.findByEmail(email);
		
		if (association == null) return "Aucun compte ne correspond ?? ce mail";
		
		sendPassword(association);
		return "Mot de passe du compte envoy??.";		
	}
	
	private void sendPassword(Association association) {
		String recipient = association.getEmail();
		String subject = "Mot de passe oubli??";
		String messageTosend = "Voici votre mot de passe :\n"
				+ association.getPassword();
		
		recipient = "crespel.romain@gmail.com";
		try {
			sendMailService.sendMail(recipient, subject, messageTosend, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping({"dashboardAsso/logout", "logout"})  
    public String logoutPage(HttpServletRequest request) {  
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect: ../index"; 
     }  
	
	@RequestMapping({"/homeDashboardAssociation", "dashboardAsso/home","home"})
	public String homeDashboardAssociation(HttpServletRequest req) {
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
			if (association.getPathLogo() != null) associationService.deleteFile(association.getPathLogo()); // The condition is necessary cause of the mock data where there isn't logo to be loaded. In case of inscription on the site an association couldn't be without logo
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
		
		deleteDocument(association);
		deleteMessaging(association);
		deleteUsers(association);
		
		associationService.deleteFolder(association.getPathFolder());
		associationService.delete(association);
		
		session.invalidate();
		
		return "redirect: ../index";
	}
	
	private void deleteDocument(Association association) {
		Document document = association.getDocument();
		document = documentService.findOne(document.getId());
		
		List<Photo> photos = document.getPhotos();
		
		for (Photo photo : new ArrayList<Photo>(photos)) {
			photoService.delete(photoService.findOne(photo.getId()));
			photos.remove(photo);
		}
		
		documentService.delete(document);
	}
	
	private void deleteMessaging(Association association) {
		Messaging messaging = association.getMessaging();
		messaging = messagingService.findOne(messaging.getId());
		
		List<Mail> mails = messaging.getMails();
		
		for (Mail mail : new ArrayList<Mail>(mails)) {
			mailService.delete(mailService.findOne(mail.getId()));
			mails.remove(mail);
		}
		
		messagingService.delete(messaging);
	}
	
	private void deleteUsers(Association association) {
		List<User> users = association.getUsers();

		for (User user : new ArrayList<User>(users)) {
			userService.delete(userService.findOne(user.getId()));
			users.remove(user);
		}
	}
	
	@GetMapping("dashboardAsso/editDocument")
	public String editDocument(HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
		
		// Reload ours objects save in session
		association = associationService.findOne(association.getId());
		
		model.addAttribute(ATT_REQUEST_DOCUMENT, association.getDocument());

		return "editDocument";
	}
	
	@PostMapping("dashboardAsso/editDocument")
	public String editDocumentForm(@RequestParam("header") MultipartFile header, @RequestParam("files") MultipartFile[] files, @ModelAttribute("requestDocument") Document documentUpdated) {
		Document document = documentService.findOne(documentUpdated.getId());
		
		document.setParagraph_1(documentUpdated.getParagraph_1());
		document.setParagraph_2(documentUpdated.getParagraph_2());
		document.setStatut(Statut.VALID);
		
		if (!header.getOriginalFilename().isEmpty()) {
			if(document.getPathHeader() != null) documentService.deleteFile(document.getPathHeader());
			 document.setPathHeader(documentService.saveFile(header, document.getPathFolder()));
		}
		
		if (files.length != 0) savePhotos(files, document);
		
		documentService.update(document);

		return "redirect: editDocument";
	}
	
	private void savePhotos(MultipartFile[] photos, Document document) {
		
		for (MultipartFile photo : photos) {
			if (photo.getOriginalFilename().isEmpty()) continue;
			Photo newPhoto = new Photo();
			newPhoto.setName(photo.getOriginalFilename().trim());
			newPhoto.setPathPhoto(photoService.saveFile(photo, document.getPathFolderPhoto()));
			newPhoto.setDocument(document);
			photoService.create(newPhoto);
		}
	}
	
	@GetMapping("dashboardAsso/messaging")
	public String messaging(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
				
		// Reload ours objects save in session
		association = associationService.findOne(association.getId());
		
		List<Mail> mails = association.getMessaging().getMails();
		Collections.reverse(mails);
		
		model.addAttribute(ATT_REQUEST_MAILS, mails);
		
		return "messaging";
	}
	
	@PostMapping("dashboardAsso/newMail")
	public @ResponseBody String newMessage(@RequestParam("attachment") MultipartFile attachment, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
				
		// Reload ours objects save in session
		association = associationService.findOne(association.getId());
		
		Mail mail = mailService.init(req);
		mail.setSender(association.getName());
		mail.setStatut(Statut.INACTIVE);
		
		String[] emails = getAllEmails(mail.getRecipient(), association);
		if (emails != null && emails.length == 0) return "Aucun utilisateur ne correspond ?? cet envoi group??";
		
		if (!attachment.getOriginalFilename().isEmpty()) {
			mail.setAttachment(mailService.saveFile(attachment, association.getMessaging().getPathFolder()));
		}
		
		if (sendMail(mail, emails)) {
			mail.setMessaging(association.getMessaging());
			mailService.create(mail);
			
			session.setAttribute(ATT_SESSION_ASSOCIATION, association);
			return "Message envoy??.";
		} else  {
			if (mail.getAttachment() != null) mailService.deleteFile(mail.getAttachment());
			
			return "Message non transmis";
		}		
	}
	
	private String[] getAllEmails(String recipient, Association association) {
		
		switch (recipient) {
			case "Donnateurs":
				List<String> mailsDonors = association.getUsers().stream().filter(x -> x.getDonations().size() > 0).map(x -> x.getEmail()).collect(Collectors.toList());
				return mailsDonors.toArray(new String[0]);
			case "B??n??voles":
				List<String> mailsVolunteers = association.getUsers().stream().filter(x -> x.getVolunteer() != null).map(x -> x.getEmail()).collect(Collectors.toList());
				return mailsVolunteers.toArray(new String[0]);
			case "Participants":
				List<String> mailsParticipants = association.getUsers().stream().filter(x -> x.getParticipant() != null).map(x -> x.getEmail()).collect(Collectors.toList());
				return mailsParticipants.toArray(new String[0]);
			case "Partenaires":
				List<String> mailsPartners = association.getUsers().stream().filter(x -> x instanceof UserSociety).filter(x -> ((UserSociety) x).getPartner() != null).map(x -> x.getEmail()).collect(Collectors.toList());
				return mailsPartners.toArray(new String[0]);
			case "Utilisateurs":
				List<String> mailsUsers = association.getUsers().stream().map(x -> x.getEmail()).collect(Collectors.toList());
				return mailsUsers.toArray(new String[0]);
		}
		
		return null;
	}
	
	private boolean sendMail(Mail mail, String[] emails) {
		
		if (emails == null) {
			try {
				return sendMailService.sendMail(mail);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			try {
				return sendMailService.sendMail(mail, emails);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	@PostMapping("dashboardAsso/getMail")
	public @ResponseBody String getMail(@RequestParam("id") String id, HttpServletRequest req) {
		Mail mail = mailService.findOne(Long.parseLong(id));
		
		if (mail.getStatut() == Statut.ACTIVE) mail.setStatut(Statut.INACTIVE);
		
		mailService.update(mail);
		
		String informations = mail.getId() + "%-%";
		
		if (mail.getMessageType() == MessageType.received) informations += mail.getSender() + "%-%";
		else informations += mail.getRecipient() + "%-%";
		
		informations += mail.getSubject() + "%-%";
		informations += mail.getContent() + "%-%";
		
		return informations;		
	}
	
	@PostMapping("dashboardAsso/deleteMail")
	public @ResponseBody String deleteMail(@RequestParam("id") String id, HttpServletRequest req) {
		Mail mail = mailService.findOne(Long.parseLong(id));
		if (mail.getAttachment() != null) mailService.deleteFile(mail.getAttachment());
		mailService.delete(mail);
		return "";		
	}
	
	@PostMapping("dashboardAsso/deletePhoto")
	public @ResponseBody String deletePhoto(@RequestParam("id") String id) {
		Photo photo = photoService.findOne(Long.parseLong(id));
		photoService.deleteFile(photo.getPathPhoto());
		photoService.delete(photo);
		return "";
	}
	
	@RequestMapping("dashboardAsso/listUsers")
	public String listUsers(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		Association association = (Association) session.getAttribute(ATT_SESSION_ASSOCIATION);
		
		association = associationService.findOne(association.getId());
		
		List<User> users = association.getUsers();
		model.addAttribute("listUsers", users);
		
		return "listUsers";
	}
		
}
