package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.isika.projet3.entities.Event;

public interface IEventService {

	Event findOne(long id);

    List<Event> findAll();

    void create(Event entity);

    Event update(Event entity);

    void delete(Event entity);

    void deleteById(long entityId);

	Event init(HttpServletRequest req);

	String createNewFolder(String pathFolder);

	void deleteFolder(String pathFolder);
}
