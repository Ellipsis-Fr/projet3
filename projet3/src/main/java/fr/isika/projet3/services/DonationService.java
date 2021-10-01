package fr.isika.projet3.services;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IDonationDao;
import fr.isika.projet3.entities.Donation;
import fr.isika.projet3.enumerations.PaymentMethod;
import fr.isika.projet3.enumerations.Statut;
import fr.isika.projet3.enumerations.TypeEvent;

@Service
@Transactional
public class DonationService implements IDonationService {
	private static final String FIELD_AMOUNT = "amount";
	private static final String FIELD_PAYMENT_METHOD = "paymentMethod";
	
	@Autowired
	private IDonationDao dao;
	
	@Override
	public Donation findOne(long id) {	
		return null;
	}

	@Override
	public List<Donation> findAll() {
		return null;
	}
	
	@Override
	public Donation init(HttpServletRequest req) {
		Statut statut = null;
		
		switch(req.getParameter(FIELD_PAYMENT_METHOD)) {
		case "0":
			statut = Statut.VALID;
			break;
		case "1":
			statut = Statut.PENDING;
			break;
		}
		
		Donation donation = new Donation();
		donation.setAmount(Integer.parseInt(req.getParameter(FIELD_AMOUNT)));
		donation.setDate(LocalDate.now());
		donation.setStatut(statut);
		
		return donation;
	}

	@Override
	public void create(Donation entity) {
		dao.create(entity);		
	}

	@Override
	public Donation update(Donation entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Donation entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

}
