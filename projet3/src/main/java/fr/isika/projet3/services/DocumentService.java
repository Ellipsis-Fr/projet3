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

import fr.isika.projet3.dao.IDocumentDao;
import fr.isika.projet3.entities.Document;

@Service
@Transactional
public class DocumentService implements IDocumentService {
	private static final String PATH_DISK ="C:/Users/micka/Documents/workspace-spring-tool-suite-4-4.11.1.RELEASE/projet3/projet3/src/main/webapp/";
	private static final int SIZE_BUFFER = 10240;

	@Autowired
	private IDocumentDao dao;
	
	@Override
	public Document findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Document entity) {
		dao.create(entity);
		
	}

	@Override
	public Document update(Document entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Document entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		dao.deleteById(entityId);
	}
	
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
	public void deleteFile(String pathHeader) {
		try {
			Files.delete(Paths.get(PATH_DISK, pathHeader));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
