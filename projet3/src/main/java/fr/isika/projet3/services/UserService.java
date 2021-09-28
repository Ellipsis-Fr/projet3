package fr.isika.projet3.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.projet3.dao.IUserDao;
import fr.isika.projet3.entities.User;

@Service
@Transactional
public class UserService implements IUserService {
	private static final String FIELD_LASTNAME = "lastname";
	private static final String FIELD_FIRSTNAME = "firstname";
	private static final String FIELD_TELEPHONE = "telephone";
	private static final String FIELD_ADDRESS = "address";
	private static final String FIELD_EMAIL = "email";
	private static final String CHECK_CONTACT = "contact";
	
	@Autowired
	private IUserDao dao;

	@Override
	public User findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User init(HttpServletRequest req) {
		User user = new User();
		
		user.setFirstname(req.getParameter(FIELD_FIRSTNAME));
		user.setLastname(req.getParameter(FIELD_LASTNAME));
		user.setTelephone(req.getParameter(FIELD_TELEPHONE));
		user.setAddress(req.getParameter(FIELD_ADDRESS));
		user.setEmail(req.getParameter(FIELD_EMAIL));
		user.setRecontactable(req.getParameter(CHECK_CONTACT) != null);
		
		return user;
	}

	@Override
	public void create(User entity) {
		dao.create(entity);
	}
	
	@Override
	public User updateByFields(User userUpdated, User userInDataBase) {
		
		userInDataBase.setFirstname(userUpdated.getFirstname());
		userInDataBase.setLastname(userUpdated.getLastname());
		userInDataBase.setTelephone(userUpdated.getTelephone());
		userInDataBase.setAddress(userUpdated.getAddress());
		userInDataBase.setRecontactable(userUpdated.isRecontactable());
		
		return userInDataBase;
	}

	@Override
	public User update(User entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

}
