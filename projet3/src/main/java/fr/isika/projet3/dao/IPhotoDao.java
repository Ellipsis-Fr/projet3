package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Photo;

public interface IPhotoDao {
	Photo findOne(long id);
	
	Photo findOneByParameters(String queryString, Object...parameters);

    List<Photo> findAll();

    void create(Photo entity);

    Photo update(Photo entity);

    void delete(Photo entity);

    void deleteById(long entityId);
}
