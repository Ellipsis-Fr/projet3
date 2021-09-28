package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.isika.projet3.entities.Donation;

public interface IDonationService {

	Donation findOne(long id);

    List<Donation> findAll();

    void create(Donation entity);

    Donation update(Donation entity);

    void delete(Donation entity);

    void deleteById(long entityId);

	Donation init(HttpServletRequest req);

}
