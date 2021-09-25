package fr.isika.projet3.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Association;

public interface IAssociationService {

	Association findOne(long id);

    List<Association> findAll();
    
    void create(Association entity);

    Association update(Association entity);
    
    Association updateByFields(Association associationUpdated, Association association);

    void delete(Association entity);

    void deleteById(long entityId);

    public String createNewFolder(String folder);
	
    String saveFile(MultipartFile file, String folder);
	
	boolean checkDouble(String rna);
	
	Association associationLogIn(String email, String password);

	void deleteFolder(String pathFolder);

	void deleteFile(String pathLogo);
}
