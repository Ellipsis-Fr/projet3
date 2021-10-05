package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.entities.Activity;

public interface IActivityService {
	
	Activity findOne(long id);

	List<Activity> findAll();

	void create(Activity entity);

	Activity update(Activity entity);

	void delete(Activity entity);

	void deleteById(long entityId);
	
	String createNewFolder(String folder);

	String saveFile(MultipartFile file, String folder);
	
	boolean checkDouble(String name);

	Activity init(HttpServletRequest req);

	void deleteFile(String pathPhoto);

	Activity updateByFields(Activity activityUpdated, Activity activity);

}
