package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Mail;

public interface IMailService {

	Mail findOne(long id);

    List<Mail> findAll();
    
    Mail init(HttpServletRequest req);
    
    void create(Mail entity);

    Mail update(Mail entity);
    
    Mail updateByFields(Mail MessageUpdated, Mail Message);

    void delete(Mail entity);

    void deleteById(long entityId);

    public String createNewFolder(String folder);
	
    String saveFile(MultipartFile file, String folder);

	void deleteFolder(String pathFolder);

	void deleteFile(String pathAttachment);
}
