package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Event;

@Repository
public class EventDao extends AbstractJpaDao<Event> implements IEventDao {
	
	public EventDao() {
		setClazz(Event.class);
	}
}
