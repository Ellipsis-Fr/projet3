package fr.isika.projet3.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Messaging;

public interface IMessagingService {

	Messaging findOne(long id);

    List<Messaging> findAll();
    
    void create(Messaging entity);

    Messaging update(Messaging entity);
    
    Messaging updateByFields(Messaging MessagingUpdated, Messaging Messaging);

    void delete(Messaging entity);

    void deleteById(long entityId);

    public String createNewFolder(String associationFolder, String folder);
	
    String saveFile(MultipartFile file, String folder);

	void deleteFolder(String pathFolder);

	void deleteFile(String pathLogo);
}
