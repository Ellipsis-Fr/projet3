package fr.isika.projet3.dao;

import org.springframework.stereotype.Repository;

import fr.isika.projet3.entities.User;

@Repository
public class UserDao extends AbstractJpaDao<User> implements IUserDao {

	public UserDao() {
		setClazz(User.class);
	}
}
