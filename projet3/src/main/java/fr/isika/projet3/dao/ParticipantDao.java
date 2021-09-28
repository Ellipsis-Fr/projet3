package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Participant;

@Repository
public class ParticipantDao extends AbstractJpaDao<Participant> implements IParticipantDao {

	public ParticipantDao() {
		setClazz(Participant.class);
	}	
}
