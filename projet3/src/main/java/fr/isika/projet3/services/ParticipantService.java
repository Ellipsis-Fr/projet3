package fr.isika.projet3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IParticipantDao;
import fr.isika.projet3.entities.Participant;

@Service
@Transactional
public class ParticipantService implements IParticipantService {

	@Autowired
	private IParticipantDao dao;
	
	@Override
	public Participant findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Participant> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Participant entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Participant update(Participant entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Participant entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

}
