package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.isika.projet3.dao.IPartnerDao;
import fr.isika.projet3.entities.Partner;
import fr.isika.projet3.enumerations.Statut;

@Service
@Transactional
public class PartnerService implements IPartnerService	{
	private static final String FIELD_PASSWORD = "password";
	private static final String FIELD_DESCRIPTION = "description";
	
	@Autowired
	private IPartnerDao partnerDao;

	@Override
	public Partner findOne(long id) {
		return partnerDao.findOne(id);
	}

	@Override
	public List<Partner> findAll() {
		return partnerDao.findAll();
	}
	
	@Override
	public Partner init(HttpServletRequest req) {
		Partner partner = new Partner();
		
		partner.setStatut(Statut.VALID);
		partner.setPassword(req.getParameter(FIELD_PASSWORD));
		partner.setDescription(req.getParameter(FIELD_DESCRIPTION));
		
		return partner;
	}

	@Override
	public void create(Partner partner) {
		partnerDao.create(partner);		
	}

	@Override
	public Partner update(Partner partner) {
		return partnerDao.update(partner);
	}

	@Override
	public void delete(Partner partner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}
}


