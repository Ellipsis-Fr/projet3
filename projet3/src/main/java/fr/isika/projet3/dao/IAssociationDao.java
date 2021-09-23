package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Association;

public interface IAssociationDao {

	Association findOne(long id);
	
	Association findOneByParameters(String queryString, Object...parameters);

    List<Association> findAll();

    void create(Association entity);

    Association update(Association entity);

    void delete(Association entity);

    void deleteById(long entityId);
    
}
