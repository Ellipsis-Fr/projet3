package fr.isika.projet3.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.dao.IActivityDao;
import fr.isika.projet3.entities.Activity;
import fr.isika.projet3.enumerations.Category;
import fr.isika.projet3.enumerations.Statut;

@Service
@Transactional
public class ActivityService implements IActivityService {
	private static final String PATH_DISK ="C:/Users/micka/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/projet3/projet3/src/main/webapp/";
	private static final int SIZE_BUFFER = 10240;
	
	private static final String NAME = "name";
	private static final String ADDRESS = "address";
	private static final String FIELD_START_DATE = "startDate";
	private static final String FIELD_END_DATE = "endDate";
	private static final String DESCRIPTION = "description";
	private static final String NECESSARY_FUNDING = "necessaryFunding";
	private static final String VOLUNTEER_NEEDED = "volunteerNeeded";
	private static final String FIELD_TYPE_CATEGORY = "category";

	@Autowired
	private IActivityDao dao;
	
	@Override
	public Activity findOne(long id) {
		return dao.findOne(id);
	}
	
	@Override
	public List<Activity> findAll() {
		return dao.findAll();
	}
	
	@Override
	public List<Activity> findAllByStatusAndEventId(Long eventId, Statut statut) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("event_id", eventId.toString());
		parameters.put("statut", Integer.toString(Statut.valueOf(statut.toString()).ordinal()));
		return dao.findAllByParameters(parameters);
	}
	
	@Override
	public Activity init(HttpServletRequest req) {
		String name = req.getParameter(NAME);
		String address = req.getParameter(ADDRESS);
		LocalDate startDate = LocalDate.parse(req.getParameter(FIELD_START_DATE));
		LocalDate endDate = LocalDate.parse(req.getParameter(FIELD_END_DATE));
		String description = req.getParameter(DESCRIPTION);
		int necessaryFunding = Integer.parseInt(req.getParameter(NECESSARY_FUNDING));
		int volunteerNeeded = Integer.parseInt(req.getParameter(VOLUNTEER_NEEDED));
		
		Category category = null;

		switch(req.getParameter(FIELD_TYPE_CATEGORY)) {
			case "0":
				category = Category.WITH_PARTICIPANT;
				break;
			case "1":
				category = Category.WITHOUT_PARTICIPANT;
				break;
		}
		
		Activity activity = new Activity();
		activity.setName(name);
		activity.setAddress(address);
		activity.setStartDate(startDate);
		activity.setEndDate(endDate);
		activity.setDescription(description);
		activity.setNecessaryFunding(necessaryFunding);
		activity.setVolunteerNeeded(volunteerNeeded);
		activity.setCategory(category);
		
		return activity;	
	}
	
	@Override
	public void create(Activity entity) {
		dao.create(entity);
	}
	
	@Override
	public Activity update(Activity entity) {
		return dao.update(entity);
	}
	
	@Override
	public Activity updateByFields(Activity activityUpdated, Activity activity) {
		
		if (activityUpdated.getName() != null && !activityUpdated.getName().isEmpty()) {
			activity.setName(activityUpdated.getName().trim());
		}

		if (activityUpdated.getAddress() != null  && !activityUpdated.getAddress().isEmpty()) {
			activity.setAddress(activityUpdated.getAddress().trim());
		}
		
		if (activityUpdated.getStartDate() != activity.getStartDate() ) {
			activity.setStartDate(activityUpdated.getStartDate());
		}
		
		if (activityUpdated.getEndDate() != activity.getEndDate() ) {
			activity.setEndDate(activityUpdated.getEndDate());
		}

		if (activityUpdated.getDescription() != null && !activityUpdated.getDescription().isEmpty()) {
			activity.setDescription(activityUpdated.getDescription().trim());
		}
		
		if (activityUpdated.getNecessaryFunding() != activity.getNecessaryFunding() ) {
			activity.setNecessaryFunding(activityUpdated.getNecessaryFunding());
		}

		if (activityUpdated.getAllocatedFunding() != activity.getAllocatedFunding() ) {
			activity.setAllocatedFunding(activityUpdated.getAllocatedFunding());
		}
		
		if (activityUpdated.getVolunteerNeeded() != activity.getVolunteerNeeded() ) {
			activity.setVolunteerNeeded(activityUpdated.getVolunteerNeeded());
		}
		
		if (activityUpdated.getVolunteerAllocated() != activity.getVolunteerAllocated() ) {
			activity.setVolunteerAllocated(activityUpdated.getVolunteerAllocated());
		}

		if (activityUpdated.getCategory() != activity.getCategory() ) {	
			activity.setCategory(activityUpdated.getCategory());
		}
				
		return activity;
	}

	@Override
	public void delete(Activity entity) {
		dao.delete(entity);		
	}

	@Override
	public void deleteById(long entityId) {
		dao.deleteById(entityId);
	}

	@Override
	public String createNewFolder(String folder) {
		Path newFolder = null;
		
		try {
			newFolder = Files.createDirectory(Paths.get(PATH_DISK, folder, "activity"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return folder + "/" + newFolder.getFileName().toString();
	}
	
	@Override
	public String createNewFolder(String folder, String additionalName) { //ADD
		Path newFolder = null;
		
		try {
			newFolder = Files.createDirectory(Paths.get(PATH_DISK, folder, "activity" + additionalName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return folder + "/" + newFolder.getFileName().toString();
	}
	
	
	@Override
	public String saveFile(MultipartFile file, String folder) {
		
		String filename = file.getOriginalFilename().trim();
		
		String shortNameFile = filename.substring(0, filename.lastIndexOf("."));
		String extensionFile = filename.substring(filename.lastIndexOf("."));
		Date date = new Date();
		String formattedDateTime = date.getTime() + "";
		
		String fileNameString = shortNameFile + formattedDateTime + extensionFile;
		
		File newFile = Paths.get(PATH_DISK, folder, fileNameString).toFile();
		System.out.println(newFile);

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
//		return  Paths.get(pathFolder.toString(),newFile.getName()).toString();
	}
	
	@Override
	public boolean checkDouble(String name) {
		String query = "FROM Activity a WHERE a.name=?"; //à modifier pour verifier pas deux dans la même asso 
		return dao.findOneByParameters(query, name) != null ? true : false;
	}
	
	@Override
	public void deleteFile(String pathPhoto) {
		try {
			Files.delete(Paths.get(PATH_DISK, pathPhoto));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
