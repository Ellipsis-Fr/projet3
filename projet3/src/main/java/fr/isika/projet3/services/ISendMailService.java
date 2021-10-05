package fr.isika.projet3.services;

import java.util.List;

import javax.mail.Message;

import fr.isika.projet3.entities.Mail;

public interface ISendMailService {
	
	boolean sendMail(String recipient, String subject, String messageToSend, String attachment) throws Exception;
	
	boolean sendMail(Mail mail) throws Exception;
	
	boolean sendMail(Mail mail, String[] emails) throws Exception;
}
