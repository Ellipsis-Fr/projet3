package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.isika.projet3.entities.User;

public interface IUserService {

	User findOne(long id);

    List<User> findAll();

    User init(HttpServletRequest req);
    
    void create(User entity);

    User updateByFields(User userUpdated, User userInDataBase);
    
    User update(User entity);

    void delete(User entity);

    void deleteById(long entityId);
}
