package fr.isika.projet3.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Photo;

public interface IPhotoService {

	Photo findOne(long id);

    List<Photo> findAll();
    
    void create(Photo entity);

    Photo update(Photo entity);
    
    Photo updateByFields(Photo PhotoUpdated, Photo Photo);

    void delete(Photo entity);

    void deleteById(long entityId);
    
    public String createNewFolder(String folder);
	
    String saveFile(MultipartFile file, String folder);

	void deleteFolder(String pathFolder);

	void deleteFile(String pathFile);
}
