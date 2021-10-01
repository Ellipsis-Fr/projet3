package fr.isika.projet3.services;

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

	@Autowired
	IPhotoDao dao;

	@Override
	public Photo findOne(long id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String createNewFolder(String folder) {
		// TODO Auto-generated method stub
		return null;
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
	public void deleteFile(String pathFile) {
		// TODO Auto-generated method stub
		
	} 
}
