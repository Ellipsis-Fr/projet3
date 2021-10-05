package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Messaging;

public interface IMessagingDao {

	Messaging findOne(long id);
	
	Messaging findOneByParameters(String queryString, Object...parameters);

    List<Messaging> findAll();

    void create(Messaging entity);

    Messaging update(Messaging entity);

    void delete(Messaging entity);

    void deleteById(long entityId);
}
