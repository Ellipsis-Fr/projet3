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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.dao.IAssociationDao;
import fr.isika.projet3.entities.Association;

@Service
@Transactional
public class AssociationService implements IAssociationService {
	private static final String PATH_DISK ="D:/Developpement/Environnement_et_Outils/Git/GitRepositories/ISIKA/projet3/projet3/src/main/webapp/ServerContent/associations/";
	private static final String PATH_SERVER ="ServerContent/associations/";
	private static final int SIZE_BUFFER = 10240;

	@Autowired
	private IAssociationDao dao;

	@Override
	public Association findOne(long id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Association entity) {
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
	public boolean checkDouble(String rna) {
		String query = "FROM Association a WHERE a.rna=?";
		return dao.findOneByParameters(query, rna) != null ? true : false;
	}

	@Override
	public Association associationLogIn(String email, String password) {
		String query = "FROM Association a WHERE a.email=? AND a.password=?";
		return dao.findOneByParameters(query, email, password);
	}
}
