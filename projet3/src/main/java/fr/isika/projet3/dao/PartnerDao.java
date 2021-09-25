package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Partner;


@Repository
public class PartnerDao  extends AbstractJpaDao<Partner> implements IPartnerDao{

	public PartnerDao() {
		setClazz(Partner.class);
	}	
}
