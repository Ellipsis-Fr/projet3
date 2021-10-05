package fr.isika.projet3.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.dao.IMessagingDao;
import fr.isika.projet3.entities.Messaging;

@Service
@Transactional
public class MessagingService implements IMessagingService {
	private static final String PATH_DISK ="D:/Developpement/Environnement_et_Outils/Git/GitRepositories/ISIKA/projet3/projet3/src/main/webapp/";
	private static final int SIZE_BUFFER = 10240;

	@Autowired
	private IMessagingDao dao;

	@Override
	public Messaging findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Messaging> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Messaging entity) {
		dao.create(entity);
	}

	@Override
	public Messaging update(Messaging entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Messaging updateByFields(Messaging MessagingUpdated, Messaging Messaging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Messaging entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String createNewFolder(String associationFolder, String folder) {
		Path newFolder = null;
		
		try {
			newFolder = Files.createDirectory(Paths.get(PATH_DISK, associationFolder, folder));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return associationFolder + "/" + newFolder.getFileName().toString();
	}

	@Override
	public String saveFile(MultipartFile file, String folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFolder(String pathFolder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(String pathLogo) {
		// TODO Auto-generated method stub
		
	}
}
