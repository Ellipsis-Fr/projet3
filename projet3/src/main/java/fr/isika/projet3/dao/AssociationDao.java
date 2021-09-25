package fr.isika.projet3.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Association;

@Repository
public class AssociationDao extends AbstractJpaDao<Association> implements IAssociationDao {

	public AssociationDao() {
		setClazz(Association.class);
	}	

}
