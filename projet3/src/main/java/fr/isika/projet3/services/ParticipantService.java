package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IParticipantDao;
import fr.isika.projet3.entities.Participant;

@Service
@Transactional
public class ParticipantService implements IParticipantService {
	private static final String FIELD_PASSWORD = "password";
	
	@Autowired
	private IParticipantDao dao;
	
	@Override
	public Participant findOne(long id) {
		return dao.findOne(id);
	}

	@Override
	public List<Participant> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Participant init(HttpServletRequest req) {
		Participant participant = new Participant();
		
		participant.setPassword(req.getParameter(FIELD_PASSWORD));
		
		return participant;
	}

	@Override
	public void create(Participant entity) {
		dao.create(entity);
	}

	@Override
	public Participant update(Participant entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Participant entity) {
		dao.delete(entity);
		
	}

	@Override
	public void deleteById(long entityId) {
		dao.deleteById(entityId);
	}
}
