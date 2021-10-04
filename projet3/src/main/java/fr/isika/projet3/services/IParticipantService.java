package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.isika.projet3.entities.IRole;
import fr.isika.projet3.entities.Participant;

public interface IParticipantService {

	Participant findOne(long id);

    List<Participant> findAll();
    
    Participant init(HttpServletRequest req);

    void create(Participant entity);

    Participant update(Participant entity);

    void delete(Participant entity);

    void deleteById(long entityId);
    
}
