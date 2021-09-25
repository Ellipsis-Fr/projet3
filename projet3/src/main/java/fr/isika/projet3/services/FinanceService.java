package fr.isika.projet3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.isika.projet3.dao.IFinanceDao;
import fr.isika.projet3.entities.Finance;

@Service
@Transactional
public class FinanceService implements IFinanceService {
	
	@Autowired
	private IFinanceDao iFinanceDao;

	@Override
	public void create(Finance finance) {
		iFinanceDao.create(finance);		
	}

}
