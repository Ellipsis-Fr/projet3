package fr.isika.projet3.services;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.StringUtils;
import fr.isika.projet3.entities.Mail;


@Service
@Transactional
public class SendMailService implements ISendMailService {
	
	private final String MAIL_SMTP_AUTH = "true";
	private final String MAIL_SMTP_STARTTLS_ENABLE = "true";
	private final String MAIL_SMTP_HOST = "smtp.gmail.com";
	private final String MAIL_SMTP_PORT = "587";
	
	private final String MAIL_SENDER = "projet3charity@gmail.com";
	private final String PASSWORD = "ProjetCharity.3+"; 

	@Override
	public boolean sendMail(String recipient, String subject, String messageToSend, String attachment) throws Exception {
		
		boolean result = false;
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", MAIL_SMTP_AUTH);
		properties.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
		properties.put("mail.smtp.host", MAIL_SMTP_HOST);
		properties.put("mail.smtp.port", MAIL_SMTP_PORT);
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_SENDER,PASSWORD);
			}
		});
		
		Message message = prepareMessage(session, MAIL_SENDER, recipient, subject, messageToSend, attachment);
		Transport.send(message);
		result = true;
		
		return result;
		
	}
	
	@Override
	public boolean sendMail(Mail mail) throws Exception {
		
		boolean result = false;
		
		Session session = getSession();
		
		Message message = prepareMessage(session, MAIL_SENDER, "crespel.romain@gmail.com", mail.getSubject(), mail.getContent(), mail.getAttachment());
		Transport.send(message);
		result = true;
		
		return result;
		
	}
	
	@Override
	public boolean sendMail(Mail mail, String[] emails) throws Exception {
		
		boolean result = false;
		
		Session session = getSession();
		
		Message message = prepareMessage(session, MAIL_SENDER, "crespel.romain@gmail.com", mail.getSubject(), mail.getContent(), mail.getAttachment());
		Transport.send(message);
		result = true;
		
		return result;
		
	}
	
	
	private Session getSession() {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", MAIL_SMTP_AUTH);
		properties.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
		properties.put("mail.smtp.host", MAIL_SMTP_HOST);
		properties.put("mail.smtp.port", MAIL_SMTP_PORT);
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_SENDER,PASSWORD);
			}
		});
		
		return session;
	}
	
	private static Message prepareMessage(Session session,String myAccountantEmail,String recipient, String subject, String messageToSend, String attachment) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountantEmail));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			message.setSubject(subject);
			Multipart multipartObject = new MimeMultipart(); 
			
			BodyPart messageBody = new MimeBodyPart();
			
			if(attachment != null && !attachment.isEmpty()) { // Envoie message et attachment
				
				// Message à envoyer
				messageBody.setText(messageToSend);
				multipartObject.addBodyPart(messageBody); 
				 
				// Attachment
				BodyPart attachmentBody = new MimeBodyPart();
	           // String filename = "E:/workspace-spring-projet3/Isika_projet3/projet3/src/main/webapp/attachment.txt";
	            String filename = attachment;
				DataSource source = new FileDataSource(filename); 
	            attachmentBody.setDataHandler(new DataHandler(source)); 
	            attachmentBody.setFileName(filename); 
	            
	            // creating MultiPart object   
	            multipartObject.addBodyPart(attachmentBody);
	            // set body of the email.
	            message.setContent(multipartObject);
			} else { // Envoie message
				 messageBody.setText(messageToSend);
				 multipartObject.addBodyPart(messageBody); 
				 
				 message.setContent(multipartObject);
			}
			return message;
		} catch (Exception ex) {
			Logger.getLogger(SendMailService.class.getName()).log(Level.SEVERE,null,ex);
		}
		return null;
	}
	
	

}