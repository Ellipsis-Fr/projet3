package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Event;

public interface IEventDao {

	Event findOne(long id);
	
	Event findOneByParameters(String queryString, Object...parameters);

    List<Event> findAll();

    void create(Event entity);

    Event update(Event entity);

    void delete(Event entity);

    void deleteById(long entityId);

}
