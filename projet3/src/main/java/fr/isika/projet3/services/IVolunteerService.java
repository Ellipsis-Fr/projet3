package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.isika.projet3.entities.Association;
import fr.isika.projet3.entities.IRole;
import fr.isika.projet3.entities.Volunteer;

public interface IVolunteerService {

	Volunteer findOne(long id);

    List<Volunteer> findAll();
    
    Volunteer init(HttpServletRequest req);

    void create(Volunteer entity);

    Volunteer update(Volunteer entity);

    void delete(Volunteer entity);

    void deleteById(long entityId);
}
