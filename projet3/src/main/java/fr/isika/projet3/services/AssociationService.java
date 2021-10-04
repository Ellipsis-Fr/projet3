package fr.isika.projet3.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.dao.IAssociationDao;
import fr.isika.projet3.entities.Association;

@Service
@Transactional
public class AssociationService implements IAssociationService {
	private static final String PATH_DISK ="D:/Developpement/Environnement_et_Outils/Git/GitRepositories/ISIKA/projet3/projet3/src/main/webapp/";
	private static final String PATH_SERVER ="ServerContent/associations";
	private static final int SIZE_BUFFER = 10240;

	@Autowired
	private IAssociationDao dao;

	@Override
	public Association findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Association> findAll() {
		return dao.findAll();
	}

	@Override
	public void create(Association entity) {
		dao.create(entity);
	}

	@Override
	public Association update(Association entity) {
		return dao.update(entity);
	}
	
	@Override
	public Association updateByFields(Association associationUpdated, Association association) {
		
		if (associationUpdated.getName() != null && !associationUpdated.getName().isEmpty()) {
			association.setName(associationUpdated.getName().trim());
		}
		
		if (associationUpdated.getUrl() != null) {
			association.setUrl(associationUpdated.getUrl().trim());
		}
		
		if (associationUpdated.getPathLogo() != null && !associationUpdated.getPathLogo().isEmpty()) {
			association.setPathLogo(associationUpdated.getPathLogo().trim());
		}
		
		if (associationUpdated.getAddress() != null  && !associationUpdated.getAddress().isEmpty()) {
			association.setAddress(associationUpdated.getAddress().trim());
		}
		
		if (associationUpdated.getEmail() != null  && !associationUpdated.getEmail().isEmpty()) {
			association.setEmail(associationUpdated.getEmail().trim());
		}
		
		if (associationUpdated.getPassword() != null && !associationUpdated.getPassword().isEmpty()) {
			association.setPassword(associationUpdated.getPassword().trim());
		}
		
		return association;
	}

	@Override
	public void delete(Association entity) {
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
			newFolder = Files.createDirectory(Paths.get(PATH_DISK, PATH_SERVER, folder));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return PATH_SERVER + "/" + newFolder.getFileName().toString();
	}
	
	@Override
	public String saveFile(MultipartFile file, String folder) {
		
		String filename = file.getOriginalFilename().trim();
		
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
	public boolean checkDouble(String rna) {
		String query = "FROM Association a WHERE a.rna=?";
		return dao.findOneByParameters(query, rna) != null ? true : false;
	}

	@Override
	public Association logIn(String email, String password) {
		String query = "FROM Association a WHERE a.email=? AND a.password=?";
		return dao.findOneByParameters(query, email, password);
	}
	
	@Override
	public Association findByEmail(String email) {
		String query = "FROM Association a WHERE a.email=?";
		return dao.findOneByParameters(query, email);
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
	
	@Override
	public void deleteFile(String pathLogo) {
		try {
			Files.delete(Paths.get(PATH_DISK, pathLogo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




