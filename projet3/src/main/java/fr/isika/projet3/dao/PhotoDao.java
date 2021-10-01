package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Photo;

@Repository
public class PhotoDao extends AbstractJpaDao<Photo> implements IPhotoDao {

	public PhotoDao() {
		setClazz(Photo.class);
	}
}
