package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Volunteer;

public interface IVolunteerDao {
	
	Volunteer findOne(long id);
	
	Volunteer findOneByParameters(String queryString, Object...parameters);

    List<Volunteer> findAll();

    void create(Volunteer entity);

    Volunteer update(Volunteer entity);

    void delete(Volunteer entity);

    void deleteById(long entityId);
}
