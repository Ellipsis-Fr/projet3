package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Activity;


public interface IActivityDao {
	Activity  findOne(long id);
	
	Activity findOneByParameters(String queryString, Object...parameters);

    List<Activity> findAll();

    void create(Activity entity);

    Activity update(Activity entity);

    void delete(Activity entity);

    void deleteById(long entityId);

}
