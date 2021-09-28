package fr.isika.projet3.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Association;
import fr.isika.projet3.entities.Donation;

@Repository
public class DonationDao extends AbstractJpaDao<Donation> implements IDonationDao {

	public DonationDao() {
		setClazz(Donation.class);
	}

}
