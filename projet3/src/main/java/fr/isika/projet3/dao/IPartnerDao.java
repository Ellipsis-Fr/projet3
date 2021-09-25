package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Partner;

public interface IPartnerDao {

	Partner findOne(long id);
	
	Partner findOneByParameters(String queryString, Object...parameters);

    List<Partner> findAll();

    void create(Partner entity);

    Partner update(Partner entity);

    void delete(Partner entity);

    void deleteById(long entityId);
}
