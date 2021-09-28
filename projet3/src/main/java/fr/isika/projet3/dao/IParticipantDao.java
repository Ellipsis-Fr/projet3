package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Participant;

public interface IParticipantDao {

	Participant findOne(long id);
	
	Participant findOneByParameters(String queryString, Object...parameters);

    List<Participant> findAll();

    void create(Participant entity);

    Participant update(Participant entity);

    void delete(Participant entity);

    void deleteById(long entityId);
}
