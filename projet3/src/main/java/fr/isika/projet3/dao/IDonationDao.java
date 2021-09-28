package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Donation;


public interface IDonationDao {

	Donation findOne(long id);
	
	Donation findOneByParameters(String queryString, Object...parameters);

    List<Donation> findAll();

    void create(Donation entity);

    Donation update(Donation entity);

    void delete(Donation entity);

    void deleteById(long entityId);
}
