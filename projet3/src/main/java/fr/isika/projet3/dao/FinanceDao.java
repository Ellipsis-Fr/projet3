package fr.isika.projet3.dao;


import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Finance;

@Repository
public class FinanceDao extends AbstractJpaDao<Finance> implements IFinanceDao {
	
	public FinanceDao() {
		setClazz(Finance.class);
	}

}
