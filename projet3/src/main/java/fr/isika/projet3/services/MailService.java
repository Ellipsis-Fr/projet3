package fr.isika.projet3.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.dao.IMailDao;
import fr.isika.projet3.entities.Mail;
import fr.isika.projet3.enumerations.MessageType;
import fr.isika.projet3.enumerations.Statut;

@Service
@Transactional
public class MailService implements IMailService {
	private static final String PATH_DISK ="D:/Developpement/Environnement_et_Outils/Git/GitRepositories/ISIKA/projet3/projet3/src/main/webapp/";
	private static final int SIZE_BUFFER = 10240;
	
	private static final String FIELD_SENDER = "email";
	private static final String FIELD_RECIPIENT = "recipient";
	private static final String FIELD_SUBJECT = "subject";
	private static final String FIELD_CONTENT = "content";
	private static final String FIELD_TYPE_MESSAGE = "typeMessage";

	@Autowired
	private IMailDao dao;
	
	@Override
	public Mail findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Mail> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Mail init(HttpServletRequest req) {
		Mail mail = new Mail();

		switch (req.getParameter(FIELD_TYPE_MESSAGE)) {
			case "received":
				mail.setSender(req.getParameter(FIELD_SENDER));
				mail.setMessageType(MessageType.received);
				mail.setStatut(Statut.ACTIVE);
				break;
			case "sent":
				mail.setRecipient(req.getParameter(FIELD_RECIPIENT));
				mail.setMessageType(MessageType.sent);
				mail.setSubject(req.getParameter(FIELD_SUBJECT));
				break;
		}
		
		mail.setContent(req.getParameter(FIELD_CONTENT));
		mail.setDate(LocalDate.now());
		
		return mail;
	}

	@Override
	public void create(Mail entity) {
		dao.create(entity);
	}

	@Override
	public Mail update(Mail entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mail updateByFields(Mail MessageUpdated, Mail Message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Mail entity) {
		dao.delete(entity);
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String createNewFolder(String folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveFile(MultipartFile file, String folder) {
		String filename = file.getOriginalFilename().trim();
		
		String shortNameFile = filename.substring(0, filename.lastIndexOf("."));
		String extensionFile = filename.substring(filename.lastIndexOf("."));
		
		filename = shortNameFile + new Date().getTime() + extensionFile;
		
		File newFile = Paths.get(PATH_DISK, folder, filename).toFile();

		try (InputStream input = file.getInputStream();
			BufferedInputStream bufferIn = new BufferedInputStream(input);
			BufferedOutputStream bufferOut = new BufferedOutputStream(new FileOutputStream(newFile))){
			
			byte[] tampon = new byte[SIZE_BUFFER];
            int longueur = 0;
            
            while ((longueur = bufferIn.read(tampon)) > 0) {
                bufferOut.write(tampon, 0, longueur);
            }

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return  folder + "/" + newFile.getName();
	}

	@Override
	public void deleteFolder(String pathFolder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(String pathAttachment) {
		try {
			Files.delete(Paths.get(PATH_DISK, pathAttachment));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
