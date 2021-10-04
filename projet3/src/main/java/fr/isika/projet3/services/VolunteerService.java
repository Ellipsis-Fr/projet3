package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IVolunteerDao;
import fr.isika.projet3.entities.Volunteer;

@Service
@Transactional
public class VolunteerService implements IVolunteerService {
	private static final String FIELD_PASSWORD = "password";

	@Autowired
	private IVolunteerDao dao;
	
	@Override
	public Volunteer findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Volunteer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Volunteer init(HttpServletRequest req) {
		Volunteer volunteer = new Volunteer();
		
		volunteer.setPassword(req.getParameter(FIELD_PASSWORD));
		
		return volunteer;
	}

	@Override
	public void create(Volunteer entity) {
		dao.create(entity);
		
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
