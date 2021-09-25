package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.Finance;

public interface IFinanceDao {
	
	void create(Finance finance);
	
	List<Finance> findListByParameters(String queryString, Object...parameters);

}
