package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.UserSociety;

@Repository
public class UserSocietyDao extends AbstractJpaDao<UserSociety> implements IUserSocietyDao {

	public UserSocietyDao() {
		setClazz(UserSociety.class);
	}
}
