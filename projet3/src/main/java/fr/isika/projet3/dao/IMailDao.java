package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Mail;

public interface IMailDao {

	Mail findOne(long id);
	
	Mail findOneByParameters(String queryString, Object...parameters);

    List<Mail> findAll();

    void create(Mail entity);

    Mail update(Mail entity);

    void delete(Mail entity);

    void deleteById(long entityId);
}
