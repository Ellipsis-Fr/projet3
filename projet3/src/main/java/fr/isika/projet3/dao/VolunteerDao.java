package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.Volunteer;

@Repository
public class VolunteerDao extends AbstractJpaDao<Volunteer> implements IVolunteerDao {
	
	public VolunteerDao() {
		setClazz(Volunteer.class);
	}	
}
