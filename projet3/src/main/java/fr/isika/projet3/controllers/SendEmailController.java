package fr.isika.projet3.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import fr.isika.projet3.services.ISendMailService;

@Controller
@Transactional
public class SendEmailController {
	
	@Autowired
	private ISendMailService sendMailService;
	
	private boolean result;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendEmail")
	public String financeForm() {

		return "sendEmail";
	}
	
	
	@PostMapping("/sendEmailAPI")
	public String sendEmailAPI(@RequestParam("recipient") String recipient, @RequestParam("subject") String subject, @RequestParam("messageToSend") String messageToSend, @RequestParam MultipartFile attachment, Model model) throws Exception {
		String filename = singleSave(attachment);
		if(filename != null) {
			result = this.sendMailService.sendMail(recipient, subject, messageToSend, filename);
			System.out.println(recipient+" "+subject+" "+messageToSend+" "+filename);
			if(!result) {
				model.addAttribute("result", "Erreur envoie mesage");
			}
		}else {
			result = this.sendMailService.sendMail(recipient, subject, messageToSend, "");
		}
		
		return "sendEmail";
	}
	
	public String singleSave(MultipartFile file){
    	String fileName = null;
    	if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File(fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return fileName;
            } catch (Exception e) {
                return fileName; // Error to load file
            }
        } else {
            return null;
        }
    }
	
	public String multipleSave(MultipartFile[] files){
		String fileName = null;
    	if (files != null && files.length >0) {
    		for(int i =0 ;i< files.length; i++){
	            try {
	                fileName = files[i].getOriginalFilename();
	                byte[] bytes = files[i].getBytes();
	                BufferedOutputStream buffStream = 
	                        new BufferedOutputStream(new FileOutputStream(new File("F:/cp/" + fileName)));
	                buffStream.write(bytes);
	                buffStream.close();
	            } catch (Exception e) {
	                return fileName;
	            }
    		}
    		return fileName;
        } else {
            return fileName;
        }
    }

}