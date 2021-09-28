package fr.isika.projet3.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IEventDao;
import fr.isika.projet3.entities.Event;
import fr.isika.projet3.enumerations.TypeEvent;


@Service
@Transactional
public class EventService implements IEventService {
	private static final String PATH_DISK ="D:/Developpement/Environnement_et_Outils/Git/GitRepositories/ISIKA/projet3/projet3/src/main/webapp/";
	
	private static final String FIELD_START_DATE = "startDate";
	private static final String FIELD_END_DATE = "endDate";
	private static final String FIELD_TYPE_EVENT = "typeEvent";
	
	@Autowired
	private IEventDao dao;
	
	@Override
	public Event findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Event> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Event init(HttpServletRequest req) {
		
		LocalDate startDate = LocalDate.parse(req.getParameter(FIELD_START_DATE));
		LocalDate endDate = LocalDate.parse(req.getParameter(FIELD_END_DATE));
		TypeEvent typeEvent = null;

		switch(req.getParameter(FIELD_TYPE_EVENT)) {
			case "0":
				typeEvent = TypeEvent.fundraising;
				break;
			case "1":
				typeEvent = TypeEvent.funndraisingAndActivities;
				break;
			case "2":
				typeEvent = TypeEvent.raffle;
				break;
		}
		
		Event event = new Event();
		event.setStartDate(startDate);
		event.setEndDate(endDate);
		event.setTypeEvent(typeEvent);

		return event;	
	}

	@Override
	public void create(Event entity) {
		dao.create(entity);		
	}

	@Override
	public Event update(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Event entity) {
		dao.delete(entity);
		
	}

	@Override
	public void deleteById(long entityId) {
		dao.deleteById(entityId);
	}
	
	@Override
	public String createNewFolder(String pathFolder) {
		Path newFolder = null;

		try {
			newFolder = Files.createDirectory(Paths.get(PATH_DISK, pathFolder, "event"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pathFolder + "/" + newFolder.getFileName().toString();
	}
	
	@Override
	public void deleteFolder(String pathFolder) {
		try {
			FileUtils.deleteDirectory(Paths.get(PATH_DISK, pathFolder).toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
