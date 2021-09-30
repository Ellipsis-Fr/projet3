package fr.isika.projet3.services;

public interface ISendMailService {
	
	public boolean sendMail(String recipient, String subject, String messageToSend, String attachment) throws Exception;
}
