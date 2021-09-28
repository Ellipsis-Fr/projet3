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
	private static final String PATH_DISK ="C:/Users/micka/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/Isika_projet3/projet3/src/main/webapp/ServerContent/associations/*/activities/";
	private static final String PATH_SERVER ="ServerContent/associations/*/activities/";
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
		return null;
	}
	
	@Override
	public List<Activity> findAll() {
		return dao.findAll();
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
		Statut statut = Statut.IN_PROGRESS;
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
		activity.setStatut(statut);
		
		return activity;	
	}
	
	@Override
	public void create(Activity entity) {
		dao.create(entity);
	}
	
	@Override
	public Activity update(Activity entity) {
		return null;
	}

	@Override
	public void delete(Activity entity) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub	
	}

	@Override
	public String createNewFolder(String folder) {
		Path newFolder = null;
		
		try {
			newFolder = Files.createDirectory(Paths.get(PATH_DISK, folder));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return PATH_SERVER + newFolder.getFileName().toString() + "/";
	}
	
	@Override
	public String saveFile(MultipartFile file, String folder) {
		
		String filename = file.getOriginalFilename().trim();
		
		String shortNameFile = filename.substring(0, filename.lastIndexOf("."));
		System.out.println("je récupère le nom du fichier");
		
		String extensionFile = filename.substring(filename.lastIndexOf("."));
		System.out.println("je récupère l'extension du fichier");
		
		Date date = new Date();
		String formattedDateTime = date.getTime() + "";
		System.out.println("je formates la date du fichier");
		
		String fileNameString = shortNameFile + formattedDateTime + extensionFile;
		System.out.println("je récupère le nom du fichier en entier");
		
		Path pathFolder = Paths.get(folder);
		Path pathServer = Paths.get(PATH_SERVER);
		folder = pathServer.relativize(pathFolder).toString();
		
		File newFile = Paths.get(PATH_DISK, folder, filename).toFile();
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
		
		return  Paths.get(pathFolder.toString(),newFile.getName()).toString();
	}
	
	@Override
	public boolean checkDouble(String name) {
		String query = "FROM Activity a WHERE a.name=?"; //à modifier pour verifier pas deux dans la même asso 
		return dao.findOneByParameters(query, name) != null ? true : false;
	}

}
