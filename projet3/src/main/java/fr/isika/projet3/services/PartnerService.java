package fr.isika.projet3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.isika.projet3.dao.IPartnerDao;
import fr.isika.projet3.entities.Partner;

@Service
@Transactional
public class PartnerService implements IPartnerService	{
	
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
	public void create(Partner partner) {
		partnerDao.create(partner);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Partner update(Partner partner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Partner partner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public  Partner partnerLogIn(String email, String password) {
		String query = "FROM Partner a WHERE a.email=? AND a.password=?";
		return partnerDao.findOneByParameters(query, email, password);
	}

}


