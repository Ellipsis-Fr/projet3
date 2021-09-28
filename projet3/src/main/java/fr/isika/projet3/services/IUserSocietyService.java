package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.isika.projet3.entities.User;
import fr.isika.projet3.entities.UserSociety;

public interface IUserSocietyService {

	UserSociety findOne(long id);
	
	UserSociety findDouble(String siret);

    List<UserSociety> findAll();

    UserSociety init(HttpServletRequest req);
    
    void create(UserSociety entity);
 
    UserSociety updateByFields(UserSociety userUpdated, UserSociety userInDataBase);
    
    UserSociety update(UserSociety entity);

    void delete(UserSociety entity);

    void deleteById(long entityId);



}
