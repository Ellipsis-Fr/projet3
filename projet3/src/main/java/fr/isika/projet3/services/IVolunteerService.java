package fr.isika.projet3.services;

import java.util.List;

import fr.isika.projet3.entities.Volunteer;

public interface IVolunteerService {

	Volunteer findOne(long id);

    List<Volunteer> findAll();

    void create(Volunteer entity);

    Volunteer update(Volunteer entity);

    void delete(Volunteer entity);

    void deleteById(long entityId);
}
