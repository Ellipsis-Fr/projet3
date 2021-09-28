package fr.isika.projet3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IVolunteerDao;
import fr.isika.projet3.entities.Volunteer;

@Service
@Transactional
public class VolunteerService implements IVolunteerService {

	@Autowired
	private IVolunteerDao dao;
	
	@Override
	public Volunteer findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Volunteer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Volunteer entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Volunteer update(Volunteer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Volunteer entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}
}
