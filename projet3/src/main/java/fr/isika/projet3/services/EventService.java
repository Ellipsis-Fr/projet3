package fr.isika.projet3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IEventDao;
import fr.isika.projet3.entities.Event;

@Service
@Transactional
public class EventService implements IEventService {
	@Autowired
	private IEventDao dao;
	
	@Override
	public Event findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Event entity) {
		dao.create(entity);		
	}

	@Override
	public Event update(Event entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Event entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

}
