package fr.isika.projet3.dao;

import java.util.List;

import fr.isika.projet3.entities.UserSociety;

public interface IUserSocietyDao {
	UserSociety findOne(long id);
	
	UserSociety findOneByParameters(String queryString, Object...parameters);

    List<UserSociety> findAll();

    void create(UserSociety entity);

    UserSociety update(UserSociety entity);

    void delete(UserSociety entity);

    void deleteById(long entityId);
}
