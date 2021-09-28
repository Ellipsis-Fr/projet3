package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.User;


public interface IUserDao {

	User findOne(long id);
	
	User findOneByParameters(String queryString, Object...parameters);

    List<User> findAll();

    void create(User entity);

    User update(User entity);

    void delete(User entity);

    void deleteById(long entityId);
}
