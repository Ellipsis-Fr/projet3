package fr.isika.projet3.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.isika.projet3.dao.IPhotoDao;
import fr.isika.projet3.entities.Photo;

@Service
@Transactional
public class PhotoService implements IPhotoService {
	private static final String PATH_DISK ="D:/Developpement/Environnement_et_Outils/Git/GitRepositories/ISIKA/projet3/projet3/src/main/webapp/";
	private static final int SIZE_BUFFER = 10240;

	@Autowired
	IPhotoDao dao;

	@Override
	public Photo findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Photo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Photo entity) {
		dao.create(entity);		
	}

	@Override
	public Photo update(Photo entity) {
		return dao.update(entity);
	}

	@Override
	public Photo updateByFields(Photo PhotoUpdated, Photo Photo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Photo entity) {
		dao.delete(entity);
		
	}

	@Override
	public void deleteById(long entityId) {
		dao.deleteById(entityId);
		
	}

	@Override
	public String createNewFolder(String folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveFile(MultipartFile file, String folder) {
		String filename = file.getOriginalFilename().trim();
		
		String shortNameFile = filename.substring(0, filename.lastIndexOf("."));
		String extensionFile = filename.substring(filename.lastIndexOf("."));
		
		filename = shortNameFile + new Date().getTime() + extensionFile;
		
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
	public void deleteFolder(String pathFolder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(String pathFile) {
		try {
			Files.delete(Paths.get(PATH_DISK, pathFile));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	} 
}
